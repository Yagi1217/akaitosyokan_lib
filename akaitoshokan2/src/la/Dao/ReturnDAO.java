package la.Dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReturnDAO extends ItemAbstDAO {
	public ReturnDAO() throws DAOException {
		getConnection();
	}

	public Boolean returnItem(String id) throws DAOException {
		PreparedStatement stRen = null;
		PreparedStatement stItem = null;


		try {
			con.setAutoCommit(false);
			String updRental = "UPDATE rental_list SET return_date = now() WHERE item_id = ? AND return_date IS NULL";

			stRen = con.prepareStatement(updRental);
			stRen.setInt(1, Integer.parseInt(id));
			int renRow = stRen.executeUpdate();
			if(renRow == 0) {
				throw new DAOException("更新できる貸出が存在しませんでした。");
			}
			
			String updItem = "UPDATE item_list SET rental_date = null WHERE id =  ?";

			stItem = con.prepareStatement(updItem);
			stItem.setInt(1, Integer.parseInt(id));
			int itemRow = stItem.executeUpdate();
			if(itemRow == 0) {
				throw new DAOException("更新できる資料が存在しませんでした。");
			}
			con.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("DBおかしくなってるかもよ");
			}
			throw new DAOException("レコードの取得に失敗しました。");

		} finally {
			try {
				stRen.close();
				stItem.close();

			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}

}

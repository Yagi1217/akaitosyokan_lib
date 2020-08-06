package la.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RentalDAO extends ItemAbstDAO {
	public RentalDAO() throws DAOException {
		getConnection();
	}

	public Boolean rentalItem(String memberId, String itemId) throws DAOException {
		PreparedStatement stRen = null;
		PreparedStatement stItem = null;


		try {
			con.setAutoCommit(false);
			String rentalSpl = "INSERT INTO rental_list (member_id,item_id,rental_date,return_scheduled)  values(?,?,now(),now() + '5 days')";

			stRen = con.prepareStatement(rentalSpl);
			stRen.setInt(1, Integer.parseInt(memberId));
			stRen.setInt(2, Integer.parseInt(itemId));
			int renRow = stRen.executeUpdate();
			if(renRow == 0) {
				throw new DAOException("貸出処理に失敗しました。");
			}
			
			String updItem = "UPDATE item_list SET rental_date = now() WHERE id = ?;";

			stItem = con.prepareStatement(updItem);
			stItem.setInt(1, Integer.parseInt(itemId));
			int itemRow = stItem.executeUpdate();
			if(itemRow == 0) {
				throw new DAOException("貸出処理に失敗しました。");
			}
			con.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
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

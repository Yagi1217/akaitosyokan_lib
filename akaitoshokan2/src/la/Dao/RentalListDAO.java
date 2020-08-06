package la.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import la.Bean.MembersBean;
import la.Bean.RentalListBean;
import la.Dao.ItemAbstDAO;

public class RentalListDAO extends ItemAbstDAO {

	public RentalListDAO() throws DAOException {
		getConnection();
	}

	public List<RentalListBean> MemberSearch(String memberId) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT rl.id,item_id,rl.rental_date,rl.return_scheduled,il.name,member_id"
					+ " FROM rental_list rl JOIN item_list il ON il.id = rl.item_id WHERE member_id = ? AND return_date is null ORDER BY return_scheduled 	,id  LIMIT 5 OFFSET 0";

			st = con.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(memberId));

			rs = st.executeQuery();

			List<RentalListBean> list = new ArrayList<RentalListBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String member_id = rs.getString("member_id");
				String item_id = rs.getString("item_id");
				String name = rs.getString("name");
				Date rental_date = rs.getDate("rental_date");
				Date return_scheduled = rs.getDate("return_scheduled");

				RentalListBean bean = new RentalListBean(id, member_id, item_id, name, rental_date, return_scheduled);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");

		} finally {
			try {
				closeAll(rs, st);

			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}
}

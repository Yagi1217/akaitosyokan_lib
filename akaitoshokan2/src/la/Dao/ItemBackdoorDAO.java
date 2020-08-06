package la.Dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import la.Bean.ItemBackdoorBean;

public class ItemBackdoorDAO extends ItemAbstDAO {
	public void ItemUpdate() throws DAOException {
		getConnection();
	}

	public List<ItemBackdoorBean> ItemSearch() throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM item_list ORDER BY id";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();

			List<ItemBackdoorBean> list = new ArrayList<ItemBackdoorBean>();
			while (rs.next()) {
				var Item_Id = rs.getInt("id");
				var item_Isbn = rs.getString("isbn");
				var item_Name = rs.getString("name");
				var rental_date = rs.getString("rental_date");
				var arrival_date = rs.getString("arrival_date");
				var discard_date = rs.getString("discard_date");
				var remarks = rs.getString("remarks");

				ItemBackdoorBean bean = new ItemBackdoorBean(Item_Id, item_Isbn, item_Name, rental_date, arrival_date,
						discard_date, remarks);
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

	public void ItemUpdate(String itemId, String item_Isbn, String item_Name, String rental_date, String arrival_date,
			String discard_date, String remarks) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "UPDATE item_list SET";

			if (!(item_Isbn == "")) {
				sql += " isbn = ?";
			}
			if (!(item_Name == "")) {
				sql += ", name = ?";
			}
			if (!(rental_date == "")) {
				sql += ", rental_date = ?";
			} else {
				sql += ", rental_date = NULL";
			}
			if (!(arrival_date == "")) {
				sql += ", arrival_date = ?";
			} else {
				sql += ", arrival_date = NULL";
			}
			if (!(discard_date == "")) {
				sql += ", discard_date = ?";
			} else {
				sql += ", discard_date = NULL";
			}
			if (!(remarks == "")) {
				sql += ", remarks = ?";
			} else {
				sql += ", remarks = NULL";
			}

			sql += " WHERE id = ?";
			st = con.prepareStatement(sql);

			int placeholder = 1;
			if (!(item_Isbn == "")) {
				st.setString(placeholder++, item_Isbn);
			}
			if (!(item_Name == "")) {
				st.setString(placeholder++, item_Name);
			}
			if (!(rental_date == "")) {
				st.setDate(placeholder++, Date.valueOf(rental_date));
			}
			if (!(arrival_date == "")) {
				st.setDate(placeholder++, Date.valueOf(arrival_date));
			}
			if (!(discard_date == "")) {
				st.setDate(placeholder++, Date.valueOf(discard_date));
			}
			if (!(remarks == "")) {
				st.setString(placeholder++, remarks);
			}
			if (!(itemId == "")) {
				st.setInt(placeholder++, Integer.parseInt(itemId));
			}
			st.execute();
			st.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの更新に失敗しました。");
		} finally {
			try {
				closeAll(rs, st);
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}

	public List<ItemBackdoorBean> memberSearch() throws DAOException{
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM members";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
			List<ItemBackdoorBean> list = new ArrayList<ItemBackdoorBean>();
			while (rs.next()) {
				var member_Id = rs.getInt("id");
				var member_Name = rs.getString("name");
				var member_Name_Kana = rs.getString("name_kana");
				var member_Address = rs.getString("address");
				var member_Tel = rs.getString("tel");
				var member_Email = rs.getString("email");
				var member_Birthday = rs.getString("birthday");
				var member_Regist_date = rs.getString("regist_date");
				var member_Release_date = rs.getString("release_date");
			
				ItemBackdoorBean bean = new ItemBackdoorBean(member_Id, member_Name, member_Name_Kana, member_Address, member_Tel, member_Email, member_Birthday, member_Regist_date, member_Release_date);
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

	public void MemberUpdate(String member_Id, String member_Name, String member_Name_Kana, String member_Address, String member_Tel, String member_Email, String member_Birthday, String member_Regist_date, String member_Release_date)  throws DAOException{
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "UPDATE members SET";
			if (!(member_Name == "")) {
				sql += " name = ?";
			}
			if (!(member_Name_Kana == "")) {
				sql += ", name_kana = ?";
			}
			if (!(member_Address == "")) {
				sql += ", address = ?";
			}
			if (!(member_Tel == "")) {
				sql += ", tel = ?";
			}
			if (!(member_Email == "")) {
				sql += ", email = ?";
			}
			if (!(member_Birthday == "")) {
				sql += ", birthday = ?";
			} else {
				sql += ", birthday = NULL";
			}
			if (!(member_Regist_date == "")) {
				sql += ", regist_date = ?";
			} else {
				sql += ", regist_date = NULL";
			}
			if (!(member_Release_date == "")) {
				sql += ", release_date = ?";
			} else {
				sql += ", release_date = NULL";
			}

			sql += " WHERE id = ?";
			st = con.prepareStatement(sql);
			
			int placeholder = 1;
			if (!(member_Name == "")) {
				st.setString(placeholder++, member_Name);
			}
			if (!(member_Name_Kana == "")) {
				st.setString(placeholder++, member_Name_Kana);
			}
			if (!(member_Address == "")) {
				st.setString(placeholder++, member_Address);
			}
			if (!(member_Tel == "")) {
				st.setString(placeholder++, member_Tel);
			}			
			if (!(member_Email == "")) {
				st.setString(placeholder++, member_Email);
			}			
			if (!(member_Birthday == "")) {
				st.setDate(placeholder++, Date.valueOf(member_Birthday));			
			}
			if (!(member_Regist_date == "")) {
				st.setDate(placeholder++, Date.valueOf(member_Regist_date));			
			}
			if (!(member_Release_date == "")) {
				st.setDate(placeholder++, Date.valueOf(member_Release_date));			
			}			
			if (!(member_Id == "")) {
				st.setInt(placeholder++, Integer.parseInt(member_Id));
			}			
			st.execute();
			st.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの更新に失敗しました。");
		} finally {
			try {
				closeAll(rs, st);
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}
}
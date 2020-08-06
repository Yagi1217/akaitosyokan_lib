package la.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import la.Bean.MembersBean;
import la.Dao.ItemAbstDAO;

public class MembersDAO extends ItemAbstDAO {

	public MembersDAO() throws DAOException {
		getConnection();
	}

	public List<MembersBean> memberSearch(String keyword, String searchMode, String searchCondition)
			throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT id,name,email FROM members";
			sql += " WHERE ";
			if (searchMode == "1") {
				sql += searchCondition + " LIKE ?";
				keyword = "%" + keyword + "%";
			} else {
				sql += searchCondition + " = ?";
			}

			st = con.prepareStatement(sql);
			if(searchMode == "1") {
				st.setString(1, keyword);
			}else {
				st.setInt(1, Integer.parseInt(keyword) );
			}
			

			rs = st.executeQuery();

			List<MembersBean> list = new ArrayList<MembersBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				MembersBean bean = new MembersBean(id, name, email);
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

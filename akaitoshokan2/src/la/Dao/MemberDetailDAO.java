package la.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import la.Bean.MembersBean;
import la.Dao.ItemAbstDAO;

public class MemberDetailDAO extends ItemAbstDAO {

	public MemberDetailDAO() throws DAOException {
		getConnection();
	}

	public MembersBean MemberSearch(int searchId)
			throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		MembersBean bean = null;

		try {
			String sql = "SELECT id,name,name_kana,address,tel,email,birthday,regist_date,release_date" + 
					" FROM members WHERE id = ?";

			st = con.prepareStatement(sql);
			st.setInt(1, searchId);

			rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String kana = rs.getString("name_kana");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				Date birthday = rs.getDate("birthday");
				Date registDate = rs.getDate("regist_date");
				Date releaseDate = rs.getDate("release_date");
			   bean = new MembersBean(id,name,kana,address,tel,email,birthday,registDate,releaseDate);
			}
			return bean;
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

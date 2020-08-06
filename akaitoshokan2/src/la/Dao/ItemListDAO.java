package la.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import la.Bean.ItemCategoryBean;
import la.Bean.ItemListBean;

public class ItemListDAO extends ItemAbstDAO {
	public ItemListDAO() throws DAOException {
		getConnection();
	}	
	
	public List<ItemListBean> ItemSearch(String titleName, String writerName, int category, String publisher, String mode) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			//戻るのは資料ID 資料名 著者名 出版社名 返却予定日
			//以下SQL文(カテゴリーと出版社は入力があった場合のみ使用するようにする)
			String sql = "SELECT item_list.id, item_list.isbn, item_list.name, item_master.author, item_master.publisher, item_list.rental_date FROM item_list JOIN item_master ON item_list.isbn = item_master.isbn WHERE item_list.name LIKE ? AND item_master.author LIKE ?";
			if (!(category == 999)) {
				sql += " AND item_master.class_code = ?";
			}
			if (!(publisher == "")) {
				sql += " AND item_master.publisher = ?";
			}
			if (mode.equals("borrowing")) {
				sql += " AND item_list.rental_date IS NULL";
			}
			if (mode.equals("return")) {
				sql += " AND item_list.rental_date IS NOT NULL";
			}
			sql += " ORDER BY item_list.id";
			
			st = con.prepareStatement(sql);
			
			//以下プレースホルダー
			int placeholder = 1;
			st.setString(placeholder++, "%" + titleName + "%");
			st.setString(placeholder++, "%" + writerName + "%");
			if (!(category == 999)) {
				st.setInt(placeholder++, category);
			}
			if (!(publisher == "")) {
				st.setString(placeholder++, publisher);
			}

			rs = st.executeQuery();

			List<ItemListBean> list = new ArrayList<ItemListBean>();
			while (rs.next()) {
				var Item_Id = rs.getInt("id");
				var item_Isbn = rs.getString("isbn");
				var item_Name = rs.getString("name");
				var writer_Name = rs.getString("author");
				var publisher_Name = rs.getString("publisher");
				var rental_Date = rs.getString("rental_date");
				var imageUrl = "";
				ItemListBean bean = new ItemListBean(Item_Id, item_Isbn, item_Name, writer_Name, publisher_Name, rental_Date, imageUrl);
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
	public List<ItemListBean> ItemDetail(int itemId) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT item_list.id, item_list.isbn, item_list.name, item_master.author, item_master.publisher, item_list.rental_date FROM item_list JOIN item_master ON item_list.isbn = item_master.isbn WHERE item_list.id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, itemId);
			rs = st.executeQuery();

			List<ItemListBean> list = new ArrayList<ItemListBean>();
			while (rs.next()) {
				var Item_Id = rs.getInt("id");
				var item_Isbn = rs.getString("isbn");
				var item_Name = rs.getString("name");
				var writer_Name = rs.getString("author");
				var publisher_Name = rs.getString("publisher");
				var rental_Date = rs.getString("rental_date");
				var imageUrl = Scraping(item_Isbn);
				ItemListBean bean = new ItemListBean(Item_Id, item_Isbn, item_Name, writer_Name, publisher_Name, rental_Date, imageUrl);
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

	public List<ItemCategoryBean> findAll(String Category) throws DAOException {		
		if (con == null) {
			getConnection();
		}
		
		PreparedStatement st = null;
		ResultSet rs = null;
		List<ItemCategoryBean> list = new ArrayList<ItemCategoryBean>();
		
		try {
			//分類情報
			if (Category.equals("classification")) {
				String sql = "SELECT * FROM classification ORDER BY code";
				st = con.prepareStatement(sql);
				rs = st.executeQuery();
				
				while (rs.next()) {
					var code = rs.getInt("code");
					var name = rs.getString("name");
					ItemCategoryBean bean = new ItemCategoryBean(code, name);
					list.add(bean);
				}
			}
			//出版社情報
			else if (Category.equals("publishers")) {
				String sql = "SELECT DISTINCT publisher FROM item_master ORDER BY publisher";
				st = con.prepareStatement(sql);
				rs = st.executeQuery();
				
				while (rs.next()) {
					var publisher = rs.getString("publisher");
					ItemCategoryBean bean = new ItemCategoryBean(publisher);
					list.add(bean);
					
				}
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
	
	public List<ItemListBean> searchIdList(String[] idList) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		List<ItemListBean> list = new ArrayList<ItemListBean>();
		
		try {
			for (String id : idList) {
				//戻るのは資料ID 資料名
				//以下SQL文(カテゴリーと出版社は入力があった場合のみ使用するようにする)
				String sql = "SELECT item_list.id, item_list.name FROM item_list where id = ?";

				System.out.println(sql);

				st = con.prepareStatement(sql);
				st.setInt(1, Integer.parseInt(id));

				ItemListBean itemBean = new ItemListBean();

				rs = st.executeQuery(); 
				while(rs.next()) {
					int item_id = Integer.parseInt(rs.getString("id"));
					String item_name = rs.getString("name");
					itemBean.setItem_Id(item_id);
					itemBean.setItem_Name(item_name);					
					list.add(itemBean);
				}	
			}
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
		return list;
	}
}

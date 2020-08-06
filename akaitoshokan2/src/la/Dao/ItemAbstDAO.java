package la.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ItemAbstDAO {
	private final static String JDBC_DRIVER = "org.postgresql.Driver";
	private final static String DB_URL = "jdbc:postgresql:sample";
	private final static String DB_USER = "student";
	private final static String DB_PASS = "himitu";

	protected Connection con;

	protected void getConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	protected void closeAll(ResultSet rs, PreparedStatement st) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		if (con != null) {
			con.close();
			con = null;
		}
	}

	protected String Scraping(String isbn) {
		//資料の画像ファイルをamazonから(無許可)持って来る(ライブラリー:jsoup.jar が必要)
		String imageUrl = "";
		try {
			String URL = "https://www.amazon.co.jp/s?k=" + isbn;
			Document doc = Jsoup.connect(URL).get();
			//↓でタグの種類を指定するらしい(スペースで複数指定可能らしい)
			Elements elm = doc.select("img");

			for (Element elms : elm) {
				//必要な情報のみに絞り込む
				if (elms.toString().contains("https://m.media-amazon")) {
					imageUrl = elms.toString();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageUrl;
	}

	@SuppressWarnings("serial")
	public class DAOException extends Exception {
		public DAOException(String message) {
			super(message);
		}
	}
}
package la.Servlet;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.Bean.ItemCategoryBean;
import la.Bean.ItemListBean;
import la.Dao.ItemListDAO;

@WebServlet("/ItemList")
public class ItemList extends ItemAbstServlet {
	private static final long serialVersionUID = 1L;

//	private final static String ACTION_PARAM = "action";
	private final static String MODE_PARAM = "mode";
	
	private final static String BORROWING_PARAM = "borrowing";
	private final static String RETURN_PARAM = "return";
	
	private final static String TITLE_NAME_PARAM = "titleName";
	private final static String WRITER_NAME_PARAM = "writerName";
	private final static String CATEGORY_PARAM = "category";
	private final static String PUBLISHER_PARAM = "publisher";

	private final static String CLASSIFICATION_PARAM = "classification";
	private final static String PUBLISHERS_PARAM = "publishers";

	
	private final static String ITEMLIST_PAGE = "/ItemList.jsp";

	public ItemList() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	   Function<String, String> function = string -> request.getParameter(string);
		try {

	       String titleName = function.apply(TITLE_NAME_PARAM);
			String writerName = function.apply(WRITER_NAME_PARAM);
			String categoryStr = function.apply(CATEGORY_PARAM);
			String publisher = function.apply(PUBLISHER_PARAM);
			String mode = function.apply(MODE_PARAM);
			
			int category = 999;
			if (!(isNullorBlank(categoryStr))) {
				category = Integer.parseInt(categoryStr);	
			}
			
			// 資料一覧のリンクから呼び出された場合
			if(isNullorBlank(mode) || mode.equals("null")) {				
				var dao = new ItemListDAO();
				List<ItemListBean> list = dao.ItemSearch(titleName, writerName, category, publisher, mode);

				request.setAttribute("itemLists", list);
				gotoPage(request, response, ITEMLIST_PAGE);
			}

			// 貸出・返却のリンクから呼び出された場合(アクセス時に一覧を表示する必要がある)
			else if(mode.equals(BORROWING_PARAM) || mode.equals(RETURN_PARAM)) {
				if (isNullorBlank(titleName) && isNullorBlank(writerName) && isNullorBlank(publisher)) {
					titleName = "";
					writerName = "";
					publisher = "";
				}

				var dao = new ItemListDAO();
				List<ItemListBean> list = dao.ItemSearch(titleName, writerName, category, publisher, mode);

				request.setAttribute("itemLists", list);
				gotoPage(request, response, ITEMLIST_PAGE);
			}

		}catch (Exception e) {
			e.printStackTrace();
			ErrorFunction(request, response, "内部エラーが発生しました。");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void init() throws ServletException {
		try {
			//menu内のカテゴリー情報を抽出する。
			ItemListDAO dao = new ItemListDAO();

			List<ItemCategoryBean> list = dao.findAll(CLASSIFICATION_PARAM);
			getServletContext().setAttribute(CLASSIFICATION_PARAM, list);

			list = dao.findAll(PUBLISHERS_PARAM);
			getServletContext().setAttribute(PUBLISHERS_PARAM, list);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}
}
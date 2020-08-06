package la.Servlet;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.Bean.ItemListBean;
import la.Dao.ItemListDAO;

@WebServlet("/Item_detail")
public class Item_detail extends ItemAbstServlet {
	private static final long serialVersionUID = 1L;

	private final static String ITEM_ID_PARAM = "itemId";
	private final static String ITEM_DETAIL_PAGE = "/ItemDetail.jsp";

	public Item_detail() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Function<String, String> function = string -> request.getParameter(string);
		try {
			// 資料名のリンクから呼び出された場合(itemIdの内容が存在しない場合)
			if (isNullorBlank(function.apply(ITEM_ID_PARAM)) || function.apply(ITEM_ID_PARAM).equals("null")) {
				ErrorFunction(request, response, "正しいアクセスを行ってください。");

			}
			int itemId = Integer.parseInt(function.apply(ITEM_ID_PARAM));

			var dao = new ItemListDAO();
			List<ItemListBean> list = dao.ItemDetail(itemId);
			
			request.setAttribute("itemLists", list);
			gotoPage(request, response, ITEM_DETAIL_PAGE);

		} catch (Exception e) {
			e.printStackTrace();
			ErrorFunction(request, response, "内部エラーが発生しました。");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void init() throws ServletException {
	}
}
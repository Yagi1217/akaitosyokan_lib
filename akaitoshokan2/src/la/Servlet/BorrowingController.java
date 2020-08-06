package la.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.Bean.ItemListBean;
import la.Bean.MembersBean;
import la.Dao.ItemListDAO;
import la.Dao.MembersDAO;

/**
 * Servlet implementation class BorrowingController
 */
@WebServlet("/BorrowingController")
public class BorrowingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		// リクエストパラメータ読み込み
		String memberid = request.getParameter("memberId");
		String[] items = request.getParameterValues("items[]");
		System.out.println(items);
		// 会員情報の取得
		try {
		MembersDAO md = new MembersDAO();
		List<MembersBean> list= md.memberSearch(memberid,"0","id");
		request.setAttribute("memberList",list);
		
		 ItemListDAO itemDAO = new ItemListDAO();
		 List<ItemListBean> itemList = itemDAO.searchIdList(items);
		 request.setAttribute("itemLists", itemList);
		 
		} catch(Exception e) {
			request.setAttribute("message", "内部エラーが発生しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
		}
		// ページの表示
		RequestDispatcher rd = request.getRequestDispatcher("/ItemBorrowingConfirmation.jsp");
		rd.forward(request, response);
		//return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}

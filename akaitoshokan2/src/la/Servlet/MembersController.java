package la.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import la.Bean.MembersBean;
import la.Dao.MembersDAO;

@WebServlet("/MembersController")
public class MembersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MembersController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String keyword = request.getParameter("keyword");
			String searchCondition = request.getParameter("searchCondition");
			String searchMode = "0";
			String searchResult = "1";

			if (keyword != null && keyword.length() > 0) {
				if (!searchCondition.equals("id")) {
					searchMode = "1";
				} else {
					try {
						Integer.parseInt(keyword);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "検索ワードには数値を指定してください");
						HttpSession session = request.getSession();
						session.setAttribute("searchResult", searchResult);
						session.setAttribute("keyword", keyword);
						session.setAttribute("searchCondition", searchCondition);
						RequestDispatcher rd = request.getRequestDispatcher("/members.jsp");
						rd.forward(request, response);
					}
				}
				MembersDAO md = new MembersDAO();
				List<MembersBean> list = md.memberSearch(keyword, searchMode, searchCondition);
				if (list.size() == 0) {
					searchResult = "0";
				}
				request.setAttribute("memberList", list);
			}
			HttpSession session = request.getSession();
			session.setAttribute("searchResult", searchResult);
			session.setAttribute("keyword", keyword);
			session.setAttribute("searchCondition", searchCondition);
			RequestDispatcher rd = request.getRequestDispatcher("/members.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "内部エラーが発生しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
		}
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

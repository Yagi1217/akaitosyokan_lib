package la.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.Bean.MembersBean;
import la.Bean.RentalListBean;
import la.Dao.MemberDetailDAO;
import la.Dao.RentalListDAO;

@WebServlet("/MemberDetailController")
public class MemberDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberDetailController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("id");

			MemberDetailDAO md = new MemberDetailDAO();
			MembersBean mb = md.MemberSearch(Integer.parseInt(id));
			request.setAttribute("member", mb);

			RentalListDAO rld = new RentalListDAO();
			List<RentalListBean> rList = rld.MemberSearch(id);
			request.setAttribute("rentalList", rList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/memberDetail.jsp");
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

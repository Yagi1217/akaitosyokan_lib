package la.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import la.Bean.MembersBean;
import la.Bean.RentalListBean;
import la.Dao.MemberDetailDAO;
import la.Dao.RentalListDAO;
import la.Dao.ReturnDAO;

@WebServlet("/ReturnController")
public class ReturnController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReturnController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			ReturnDAO reDao = new ReturnDAO();
			String[] id = request.getParameterValues("id");
			int count = 0;
			for(String returnId : id) {
				reDao.returnItem(returnId);				
				count++ ;
			}
			JOptionPane.showMessageDialog(null,  count + "冊返却致しました。");
			String memberId = request.getParameter("memberId");
			if(memberId == null || memberId.length() == 0) {
				RequestDispatcher rd = request.getRequestDispatcher("/ItemList?mode=return");
				rd.forward(request, response);
			} 
			else {

				MemberDetailDAO md = new MemberDetailDAO();
				MembersBean mb = md.MemberSearch(Integer.parseInt(memberId));
				request.setAttribute("member", mb);

				RentalListDAO rld = new RentalListDAO();
				List<RentalListBean> rList = rld.MemberSearch(memberId);
				request.setAttribute("rentalList", rList);
			
				RequestDispatcher rd = request.getRequestDispatcher("/memberDetail.jsp");
				rd.forward(request, response);
			}
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

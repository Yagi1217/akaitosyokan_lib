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

import la.Bean.ItemListBean;
import la.Dao.ItemAbstDAO.DAOException;
import la.Dao.ItemListDAO;
import la.Dao.RentalDAO;
import la.Dao.ReturnDAO;

@WebServlet("/RentalController")
public class RentalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RentalController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String memberId = request.getParameter("menberId");
		String[] itemId = request.getParameterValues("items[]");
		
		try {
			RentalDAO reDao = new RentalDAO();
			int count = 0;
			for(String id : itemId) {
				reDao.rentalItem(memberId, id);			
				count++;
			}
			JOptionPane.showMessageDialog(null,  count + "冊貸出致しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/ItemList.jsp");
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

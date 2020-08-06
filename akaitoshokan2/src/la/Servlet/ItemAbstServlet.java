package la.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public abstract class ItemAbstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String ERROR_PAGE = "/errInternal.jsp";
	
	protected void gotoPage(HttpServletRequest req, HttpServletResponse resp, String page) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(page);
		rd.forward(req, resp);
	}
	
	protected int getIntParameter(HttpServletRequest req, String param) {
		int ret = 0;
		try {
			ret = Integer.parseInt(req.getParameter(param));
		} catch (NumberFormatException e) {
			
		}
		return ret;
	}
	protected boolean isNullorBlank(String num) {
		// 変数の中身が空白か0桁の文字列か？
		return num == null || num.length() == 0;
		
	}
	
	protected void ErrorFunction(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		gotoPage(request, response, ERROR_PAGE);
	}
	
}

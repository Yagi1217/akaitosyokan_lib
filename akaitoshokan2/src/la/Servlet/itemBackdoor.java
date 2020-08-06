package la.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.Bean.ItemBackdoorBean;
import la.Dao.ItemBackdoorDAO;
import la.Servlet.ItemAbstServlet;

@WebServlet("/itemBackdoor")
public class itemBackdoor extends ItemAbstServlet {
	private static final long serialVersionUID = 1L;

	private final static String ITEMLIST_PAGE = "/ItemBackdoor.jsp";

	public itemBackdoor() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			String mode = request.getParameter("mode");
			String action = request.getParameter("action");
			if (isNullorBlank(mode) || mode.equals("item")) {
				if (isNullorBlank(action) || action.equals("null")) {
					var dao = new ItemBackdoorDAO();
					List<ItemBackdoorBean> list = dao.ItemSearch();

					request.setAttribute("itemLists", list);
					gotoPage(request, response, ITEMLIST_PAGE);

				} else if (action.equals("update")) {
					String itemId[] = request.getParameterValues("item_Id");
					String item_Isbn[] = request.getParameterValues("item_Isbn");
					String item_Name[] = request.getParameterValues("item_Name");
					String rental_date[] = request.getParameterValues("rental_date");
					String arrival_date[] = request.getParameterValues("arrival_date");
					String discard_date[] = request.getParameterValues("discard_date");
					String remarks[] = request.getParameterValues("remarks");

					for (int i = 0; i < itemId.length; i++) {
						var dao = new ItemBackdoorDAO();
						dao.ItemUpdate(itemId[i], item_Isbn[i], item_Name[i], rental_date[i], arrival_date[i],
								discard_date[i], remarks[i]);

					}

					// ↓actionを抜かすと何も表示されないので注意
					gotoPage(request, response, "/itemBackdoor?mode=item&action=");
				}
			} else if (isNullorBlank(mode) || mode.equals("members")) {
				if (isNullorBlank(action) || action.equals("null")) {

					var dao = new ItemBackdoorDAO();
					List<ItemBackdoorBean> list = dao.memberSearch();

					request.setAttribute("memberLists", list);
					gotoPage(request, response, ITEMLIST_PAGE);
				} else if (action.equals("update")) {
					String member_Id[] = request.getParameterValues("member_Id");
					String member_Name[] = request.getParameterValues("member_Name");
					String member_Name_Kana[] = request.getParameterValues("member_Name_Kana");
					String member_Address[] = request.getParameterValues("member_Address");
					String member_Tel[] = request.getParameterValues("member_Tel");
					String member_Email[] = request.getParameterValues("member_Email");
					String member_Birthday[] = request.getParameterValues("member_Birthday");
					String member_Regist_date[] = request.getParameterValues("member_Regist_date");
					String member_Release_date[] = request.getParameterValues("member_Release_date");

					for (int i = 0; i < member_Id.length; i++) {
						var dao = new ItemBackdoorDAO();
						dao.MemberUpdate(member_Id[i], member_Name[i], member_Name_Kana[i], member_Address[i], member_Tel[i],
								member_Email[i], member_Birthday[i], member_Regist_date[i], member_Release_date[i]);

					}

					// ↓actionを抜かすと何も表示されないので注意
					gotoPage(request, response, "/itemBackdoor?mode=members&action=");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ErrorFunction(request, response, "内部エラーが発生しました。");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
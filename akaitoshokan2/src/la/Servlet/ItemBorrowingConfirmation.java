package la.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.When;

import la.Bean.ItemBorrowingBean;
import la.Bean.ItemListBean;
import la.Dao.ItemListDAO;


@WebServlet("/ItemBorrowingConfirmation")
public class ItemBorrowingConfirmation extends ItemAbstServlet {
	private static final long serialVersionUID = 1L;

//	private final static String ACTION_PARAM = "action";
	private final static String MODE_PARAM = "mode";
	
	private final static String BORROWING_PARAM = "borrowing";
	private final static String RETURN_PARAM = "return";
	
	private final static String ITEM_ID_PARAM = "id";
	private final static String ITEM_NAME_PARAM = "item_Name";

	private final static String ITEM_BORROWING_CONFIRMATION_PAGE = "/ItemBorrowingConfirmation.jsp";
	
    public ItemBorrowingConfirmation() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//	   Function<String[], String[]> function = string[] -> request.getParameterValues(string[]);

//	   HttpSession session = request.getSession(false);
	   
	   String itemId[] = request.getParameterValues(ITEM_ID_PARAM);
//	   String itemName[] = request.getParameterValues(ITEM_NAME_PARAM);
	   for (String id : itemId) {
		   System.out.println(id);
	   }
	   
	   try {
		   ItemListDAO itemDAO = new ItemListDAO();
		   List<ItemListBean> list = itemDAO.searchIdList(itemId);
		   
		   request.setAttribute("itemLists", list);
		   
	   } catch (Exception e) {
			e.printStackTrace();
			ErrorFunction(request, response, "内部エラーが発生しました。");
		}
	   
		gotoPage(request, response, ITEM_BORROWING_CONFIRMATION_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

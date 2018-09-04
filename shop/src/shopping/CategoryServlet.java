package shopping;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//カテゴリ処理クラス

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//カテゴリーの値を取得
		request.setCharacterEncoding("UTF-8");
		String cgname = request.getParameter("category");

		//リクエストスコープにカテゴリーの値をセット
		request.setAttribute("cgname", cgname);


		Shopping shopping = new Shopping();
		ArrayList<ItemBean> itemList = shopping.getItem();

		// 商品一覧をリクエストスコープの属性にセット
		request.setAttribute("itemList", itemList);

		// 商品一覧画面に移動
		RequestDispatcher rd = request.getRequestDispatcher("./contents/itemList.jsp");
		rd.forward(request, response);
	}
}

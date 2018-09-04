package shopping;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Java入門 購入結果ページ処理クラス.
 */
@WebServlet("/ResultServlet_ans")
public class ResultServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
    public ResultServlet() {
        super();
    }

    /**
     * POSTメソッドで呼び出された場合の処理.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 購入確認画面にセットしていた値を取得（hiddenパラメータ）
		String id = ((LoginUserBean)request.getSession().getAttribute("user_db")).getId();
		String itemId  = request.getParameter("item_id");
		String quantity= request.getParameter("item_quantity");
		ShoppingDao dao = new ShoppingDao();


		//在庫の値取得用
		int quantityList = 0;
		//入荷日の値取得用
		String dateList = "";

		ResultSet rsq = null;


		try {
			// 商品IDと購入数を基にデータベースを更新
			// 対象の商品在庫をマイナスする
			dao.updateItem(itemId, Integer.parseInt(quantity));
			dao.updateHistory(id, itemId, Integer.parseInt(quantity));


			// 商品IDを元に在庫、入荷日を取得
			rsq =dao.selectQuantity(itemId);
				quantityList =rsq.getInt("quantity");
				dateList = rsq.getString("arrival_day");

			//stokテーブルに日付が格納されておらず、在庫が0なら本日の日付を格納
			if(quantityList == 0 && dateList ==null) {
				dao.updateDate(itemId);
			}

		} catch(SQLException e) {

			e.printStackTrace();

		} finally {

			dao.close();
		}

		// 商品一覧画面に移動
		RequestDispatcher rd = request.getRequestDispatcher("./contents/result.jsp");
		rd.forward(request, response);
	}
}
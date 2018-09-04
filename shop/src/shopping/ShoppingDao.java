package shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Java入門 ショッピング風DAOクラス.
 */
public class ShoppingDao {

	private Connection con = null;
	private ResultSet  rs  = null;
	private PreparedStatement ps = null;

	/**
	 * データベースの商品と在庫を取得します.
	 * @return	商品情報（ResultSet）
	 * @throws SQLException
	 */
	public ResultSet selectItem() throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
											  "root",
											  "shinkomo1");
			// SQL文を生成（カテゴリー追加）
			ps = con.prepareStatement("select item.item_id, item.item_name, item.price, stok.quantity, item.category, item.evaluation, item.evaluation_count, stok.arrival_day from item inner join stok on item.item_id = stok.item_id");

			// SQLを実行
			rs = ps.executeQuery();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}

		return rs;
	}

	/**
	 * 商品IDを基にデータベースの商品情報と在庫と評価を取得します.
	 * @return	商品情報（ResultSet）
	 * @param itemId 商品ID
	 * @throws SQLException
	 */
	public ResultSet selectItem(String itemId) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
											  "root",
											  "shinkomo1");
			// SQL文を生成
			ps = con.prepareStatement("select item.item_name, item.price, stok.quantity from item inner join stok on item.item_id = stok.item_id where item.item_id = ?");
			// SQL文に商品IDを設定
			ps.setString(1, itemId);
			// SQLを実行
			rs = ps.executeQuery();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}

		return rs;
	}

	/**
	 * ユーザの購入履歴を取得します.
	 * @return	購入履歴（ResultSet）
	 * @throws SQLException
	 */
	public ResultSet selectHistory(String id) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
											  "root",
											  "shinkomo1");
			// SQL文を生成
			ps = con.prepareStatement("select history.item_id, item.item_name, history.quantity from history inner join item on history.id = ? and history.item_id = item.item_id");
			ps.setString(1, id);

			// SQLを実行
			rs = ps.executeQuery();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}

		return rs;
	}

	/**
	 * 商品IDを基にデータベースの在庫を更新（マイナス）します.
	 * @param itemId 	商品ID
	 * @param quantity	数量
	 * @throws SQLException
	 */
	public void updateItem(String itemId, int quantity) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
											  "root",
											  "shinkomo1");

			// SQL文を生成
			ps = con.prepareStatement("update stok set quantity = quantity - ? where item_id = ? ");
			// SQL文に数量を設定
			ps.setInt(1, quantity);
			// SQL文に商品IDを設定
			ps.setString(2, itemId);
			// SQLを実行
			ps.executeUpdate();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
	}

	/**
	 * 購入履歴テーブルを更新します.
	 * @param id		ユーザID
	 * @param itemId 	商品ID
	 * @param quantity	数量
	 * @throws SQLException
	 */
	public void updateHistory(String id, String itemId, int quantity) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
											  "root",
											  "shinkomo1");

			// SQL文を生成　履歴を追加
			ps = con.prepareStatement("insert into history (id, item_id, quantity) values (?, ?, ?)");
			ps.setString(1, id);
			ps.setString(2, itemId);
			ps.setInt(3, quantity);

			ps.executeUpdate();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
	}

	/**
	 * コネクションをクローズします.
	 */
	public void close() {

		try {

			// データベースとの接続を解除する
			if(con != null) {
				con.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}

		} catch (SQLException se) {

			// データベースとの接続解除に失敗した場合
			se.printStackTrace();
		}
	}

	/**
	 * 商品IDを基に評価合計を更新（プラス）します.
	 * @param itemId 	商品ID
	 * @param quantity	数量
	 * @throws SQLException
	*/
	public void updateEvaluation(String itemId, Integer value) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
											  "root",
											  "shinkomo1");

			// SQL文を生成
			ps = con.prepareStatement("update item set evaluation = evaluation + ? where item_id = ? ");
			// SQL文に数量を設定
			ps.setInt(1, value);
			// SQL文に商品IDを設定
			ps.setString(2, itemId);
			// SQLを実行
			ps.executeUpdate();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
	}

	/**
	 * 商品IDを基に評価回数を更新（プラス）します.
	 * @param itemId 	商品ID
	 * @param quantity	数量
	 * @throws SQLException
	*/
	public void updateEvaluationCount(String itemId) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
											  "root",
											  "shinkomo1");

			// SQL文を生成
			ps = con.prepareStatement("update item set evaluation_count = (evaluation_count + 1) where item_id = ? ");
			// SQL文に商品IDを設定
			ps.setString(1, itemId);
			// SQLを実行
			ps.executeUpdate();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
	}



	/**
	 * 商品IDを基にデータベースの在庫と入荷日を取得します.
	 * @return	在庫情報と入荷日（ResultSet）
	 */
	public ResultSet selectQuantity(String itemId) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
											  "root",
											  "shinkomo1");
			// SQL文を生成
			ps = con.prepareStatement("select stok.quantity, stok.arrival_day from  stok where stok.item_id = ?");

			// SQL文に商品IDを設定
			ps.setString(1, itemId);
			// SQLを実行
			rs = ps.executeQuery();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}

		return rs;
	}

	/**
	 * 売り切れの場合、商品IDを基にデータベースの入荷日を更新します.
	 * @return	日時情報（ResultSet）
	 */
	public void updateDate(String itemId) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample",
											  "root",
											  "shinkomo1");

			// SQL文を生成
			ps = con.prepareStatement("update stok set arrival_day = curdate() where item_id = ? ");
			// SQL文に商品IDを設定
			ps.setString(1, itemId);
			// SQLを実行
			ps.executeUpdate();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
	}

}
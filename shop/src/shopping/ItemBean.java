package shopping;

import java.io.Serializable;
import java.sql.Date;

/**
 * Java入門 商品情報クラス.
 */
public class ItemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String itemId;		// 商品ID
	private String itemName;	// 商品名
	private int price;			// 商品価格
	private int quantity;		// 商品数
	private String category;//商品カテゴリー
	private int evaluation;  //評価合計
	private int evaluationCount; //評価回数
	private  Date arrivalDay;

	/**
	 * コンストラクタ.<br>
	 * メンバ変数の値を初期化します.
	 */
	public ItemBean() {
		itemId   = "";
		itemName = "";
		price = 0;
		quantity = 0;
		category = "";
		evaluation = 0;
		evaluationCount = 0;
		arrivalDay = null;
	}

	/**
	 * 商品IDを返却します.
	 * @return 商品ID
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * 商品IDを設定します.
	 * @param itemId 商品ID
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * 商品名を返却します.
	 * @return 商品名
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 商品名を設定します.
	 * @param itemName	商品名
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 商品価格を返却します.
	 * @return	商品価格
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * 商品価格を設定します.
	 * @param price	商品価格
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * 商品数を返却します.
	 * @return	商品数
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 商品数を設定します.
	 * @param stok	商品数
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * カテゴリーを設定します.
	 * @param category	商品カテゴリー
	 */
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 評価合計を設定します.
	 * @param evaluation	評価合計
	*/
	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	/**
	 * 評価回数を設定します.
	 * @param evaluation	評価回数
	*/
	public int getEvaluationCount() {
		return evaluationCount;
	}

	public void setEvaluationCount(int evaluationCount) {
		this.evaluationCount = evaluationCount;
	}

	public Date getArrivalDay() {
		return arrivalDay;
	}

	public void setArrivalDay(Date arrivalDay) {
		this.arrivalDay = arrivalDay;
	}

}
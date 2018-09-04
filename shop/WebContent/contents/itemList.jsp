<%@ page import="java.util.ArrayList"%>
<%@ page import="shopping.ItemBean"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%-- Java入門 商品一覧画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Java入門</title>
		<link href="/shop/contents/css/shopping.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<h1>商品一覧</h1>

		<%--カテゴリーリストボックス--%>
		<form action="./CategoryServlet">
			<div align="right">
				<select name= category>
					<option value=全て>全て</option>
					<option value=食品>食品</option>
					<option value=文房具>文房具</option>
					<option value=おもちゃ>おもちゃ</option>
				</select>
				<input class="common_button" type="submit" value="選択" name="選択">
			</div><br>
			</form>

		<%-- リクエストスコープからBeanクラスの配列を取得（eclipseの警告が出ても今回は大丈夫です） --%>
		<% ArrayList<ItemBean> itemList = (ArrayList<ItemBean>)request.getAttribute("itemList"); %>

		<% //Beanに格納されたカテゴリーを入れる変数
			   String beanCgn="";
			   //リクエストスコープからカテゴリー取得
			   String cgn =(String)request.getAttribute("cgname");
		%>

		<form action="./BuyItemServlet_ans">
			<table class="table_list">
				<tbody>
					<tr>
						<th>商品ID</th>
						<th>商品名</th>
						<th>価格</th>
						<th>在庫数</th>
						<th>数量</th>
						<th></th>
						<th>評価</th>
					</tr>

				<%-- Beanの要素数分（商品の種類分）テーブルを作成 --%>
				<%-- ログイン画面から遷移した場合かカテゴリー「全て」が選択された場合 --%>
				<% if(cgn == null || "全て".equals(cgn)){ %>
					<% for(ItemBean bean : itemList) { %>
					<tr>
						<%-- 商品ID --%>
						<td><%= bean.getItemId() %></td>
						<%-- 商品名 --%>
						<td><%= bean.getItemName() %></td>
						<%-- 価格 --%>
						<td class="int"><%= bean.getPrice() %></td>
						<%-- 数量（在庫） --%>
						<td class="int"><%= bean.getQuantity() %></td>


						<% if(bean.getQuantity() != 0) { %>
						<%-- 数量（在庫）がある場合はリストボックスをと購入ボタンを表示 --%>
						<td>
							<select class="list" name="<%= bean.getItemId() %>list">


							<%-- 在庫の数 リストボックス「1～」表示 --%>
								<% for(int i = 0; i <= bean.getQuantity(); i++) { %>
									<option value=<%= i %>><%= i %></option>
								<% } %>
							</select>
						</td>

						<td class="button">
							<input class="common_button" type="submit" value="購入" name="<%= bean.getItemId() %>">
						</td>

						<%-- 数量（在庫）がない場合は「売り切れ！」と表示 --%>
						<% } else { %>
						<td></td>
						<td class="button">売り切れ<br>
						次回入荷日<%--
						SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
						String day = df.format(bean.getArrivalDay());
						if(day != null){--%>
						<%--= --%>
						<%--}--%>

						</td>
						<% } %>

						<%-- 評価を表示 --%>
						<td>
						<div align="center">
							<%
								double evaluationTotal =  bean.getEvaluation();
								double evaluationCount = bean.getEvaluationCount();
								double evaluationAverage = 0;;
								int evaluationAverageInt = 0;

								if(evaluationCount != 0){
									evaluationAverage = Math.round(evaluationTotal / evaluationCount);
									evaluationAverageInt = (int)evaluationAverage;
								}else{
									evaluationAverage = 0;
								}
							%>
							<%= evaluationAverageInt %><br>

							<select name="<%= bean.getItemId() %>eva">
							<option value="5">5</option>
							<option value="4">4</option>
							<option value="3">3</option>
							<option value="2">2</option>
							<option value="1">1</option>
							</select>

							<input type="submit" value="評価" name="<%= bean.getItemId() %>" formaction="./evaluationServlet"/>
							</div>
						</td>
					</tr>
					<% } %>


				<%-- カテゴリー「全て」以外が選択された場合 --%>
				<% } else {
							for(ItemBean bean : itemList) {
								beanCgn=bean.getCategory();
									if(cgn.equals(beanCgn)){%>

					<tr>
					    <%-- 商品ID --%>
						<td><%= bean.getItemId() %></td>
						<%-- 商品名 --%>
						<td><%= bean.getItemName() %></td>
						<%-- 価格 --%>
						<td class="int"><%= bean.getPrice() %></td>
						<%-- 数量（在庫） --%>
						<td class="int"><%= bean.getQuantity() %></td>

						<% if(bean.getQuantity() != 0) { %>
						<%-- 数量（在庫）がある場合はリストボックスをと購入ボタンを表示 --%>
						<td>
							<select class="list" name="<%= bean.getItemId() %>list">


							<%-- 在庫の数 リストボックス「1～」表示 --%>
								<% for(int i = 0; i <= bean.getQuantity(); i++) { %>
									<option value=<%= i %>><%= i %></option>
								<% } %>
							</select>
						</td>

						<td class="button">
							<input class="common_button" type="submit" value="購入" name="<%= bean.getItemId() %>">
						</td>

						<%-- 数量（在庫）がない場合は「売り切れ！」と表示 --%>
						<% } else { %>
						<td></td>
						<td class="button">売り切れ<br>
						次回入荷日<%--
						SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						String day = df.format(bean.getArrivalDay());
						if(day != null){--%>
						<%--=  --%>
						<%--}--%>
						</td>
						<% } %>

						<%-- 評価を表示 --%>
						<td>
							<div align="center">
							<%
								double evaluationTotal =  bean.getEvaluation();
								double evaluationCount = bean.getEvaluationCount();
								double evaluationAverage = 0;
								int evaluationAverageInt = 0;

								if(evaluationCount != 0){
									evaluationAverage = Math.round(evaluationTotal / evaluationCount);
									evaluationAverageInt = (int)evaluationAverage;
								}else{
									evaluationAverage = 0;
								}
							%>
							<%= evaluationAverageInt %><br>

							<select name="<%= bean.getItemId() %>eva">
							<option value="5">5</option>
							<option value="4">4</option>
							<option value="3">3</option>
							<option value="2">2</option>
							<option value="1">1</option>
							</select>

							<input type="submit" value="評価" name="<%= bean.getItemId() %>" formaction="./evaluationServlet"/>
							</div>
						</td>
					</tr>
					<% }%>
					<% }%>
				<% } %>

				</tbody>
			</table>
			<input class="common_button" type="button" onclick="location.href='./contents/login.jsp'" value="戻る">
		</form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%-- Java入門 ログインエラー画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Java入門</title>
		<link href="/shop/contents/css/shopping.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<h1>ログインエラー</h1>
		<p>入力されたユーザは存在しません...</p>
		<form>
			<input class="common_button" type="button" onclick="location.href='./contents/login.jsp'" value="戻る"/>
		</form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>貸出確認</title>
</head>
<body>

	<div>
		<a href="menu.html">メニューへ</a>
	</div>
	<!-- TODO:入力処理 -->
	<c:choose>
			<c:when test="${!empty memberList}">
		<form action="/akaitoshokan/RentalController" method="post">
				<div align="center">以下の内容で貸し出してもいいですか？</div>
				<c:forEach items="${memberList}" var="memberList">
					<div align="center">会員ID：<input type="text" name="menberId" value="${memberList.id}" readonly></div>
					<div align="center">会員名：<input type="text" name="menberName" value="${memberList.name}" readonly></div>
				</c:forEach>
				<c:forEach items="${itemLists}" var="item">
					<input type="hidden" name="items[]" value="${item.item_Id}">
				</c:forEach>
				<div  align="center"><input type="submit" value="貸出"></div>
		</form>
			</c:when>
		<c:otherwise>
			<div>
				<form action="/akaitoshokan/BorrowingController" method="post">
					<div align="center">会員IDを入力してください</div>
					<div align="center">
						会員ID: <input type="text" name="memberId">
					</div>
					<c:forEach items="${itemLists}" var="item">
						<input type="hidden" name="items[]" value="${item.item_Id}">
					</c:forEach>
					<div align="center">
						<input type="submit" value="入力">
					</div>
				</form>
			</div>
		</c:otherwise>
	</c:choose>
	<hr size="3" color="blue">
	<div align="center"><h2>選択された資料一覧</h2></div>
	<div align="center">
		<table border="1">
			<tr>
				<th width="10%">資料ID</th>
				<th width="90%">資料名</th>
			</tr>
			
			<c:forEach items="${itemLists}" var="item">
				<tr>
					<td>${item.item_Id}</td>
					<td>${item.item_Name}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>
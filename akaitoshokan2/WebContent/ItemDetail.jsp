<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:forEach items="${itemLists}" var="itemList">
	<title>${itemList.item_Name }</title>
</c:forEach>
</head>
<body>
<% String value = "akaitoshokan"; %>

<a href="/<%= value %>/menu.html">メニューへ</a> -> <a href="/<%= value %>/ItemList?mode=${param.mode}">一覧ページへ</a><br>
<br>
<div style="margin : 0rem 0.5rem">
<table border="1">
	<c:forEach items="${itemLists}" var="itemList">
	<tr>
		<td rowspan="5">
			<a href="http://amazon.jp/s?k=${itemList.item_Isbn }">Amazon<br>
			${itemList.imageUrl }</a>
		</td>
		<th><h1>資料ID</h1></th>
		<td><h1>${param.itemId}</h1></td>
	</tr>
	<tr>
		<th><h1>資料名</h1></th>
		<td><h1>${itemList.item_Name }</h1></td>
	</tr>	
	<tr>
		<th><h1>著者名</h1></th>
		<td><h1>${itemList.writer_Name }</h1></td>
	</tr>
	<tr>
		<th><h1>出版社名</h1></th>
		<td><h1>${itemList.publisher_Name }</h1></td>
	</tr>
	<c:if test="${not empty itemList.rental_Date }">
	<tr>
		<th><h1>返却予定日</h1></th>
		<td><h1>${itemList.rental_Date }</h1></td>
	</tr>
	</c:if>
	
	</c:forEach>
</table>
<br><hr>
</div>
</body>
</html>
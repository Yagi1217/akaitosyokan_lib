<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<a href="/akaitoshokan/menu.html">メニューへ</a> -> <a href="/akaitoshokan/itemBackdoor?mode=members">会員情報へ</a> <-> <a href="/akaitoshokan/itemBackdoor?mode=item">資料情報へ</a>
<hr>

<form action="/akaitoshokan/itemBackdoor?mode=${param.mode}&action=update" method="post">
<table border="1">
<c:choose>
	<c:when test="${!empty itemLists}">
		<c:forEach items="${itemLists}" var="itemList">
		<tbody>
			<tr>
			</tr>
			<tr>
				<td><input type="text" name="item_Id" value="${itemList.item_Id }" readonly>	</td>
				<td><input type="text" name="item_Isbn" value="${itemList.item_Isbn }"></td>
				<td><input type="text" name="item_Name" value="${itemList.item_Name }"></td>
				<td><input type="text" name="rental_date" value="${itemList.rental_date }"></td>
				<td><input type="text" name="arrival_date" value="${itemList.arrival_date }">	</td>
				<td><input type="text" name="discard_date" value="${itemList.discard_date }"></td>
				<td><input type="text" name="remarks" value="${itemList.remarks }"></td>
			</tr>
		</tbody>
		</c:forEach>
	</c:when>
	<c:when test="${!empty memberLists}">
			<c:forEach items="${memberLists}" var="memberList">
		<tbody>
			<tr>
			</tr>
			<tr>
					<td><input type="text" name="member_Id" value="${memberList.member_Id }" readonly>	</td>
					<td><input type="text" name="member_Name" value="${memberList.member_Name }" required>	</td>
					<td><input type="text" name="member_Name_Kana" value="${memberList.member_Name_Kana }" required>	</td>
					<td><input type="text" name="member_Address" value="${memberList.member_Address }" required>	</td>
					<td><input type="text" name="member_Tel" value="${memberList.member_Tel }" required>	</td>
					<td><input type="text" name="member_Email" value="${memberList.member_Email }" required>	</td>
					<td><input type="text" name="member_Birthday" value="${memberList.member_Birthday }">	</td>
					<td><input type="text" name="member_Regist_date" value="${memberList.member_Regist_date }">	</td>
					<td><input type="text" name="member_Release_date" value="${memberList.member_Release_date }">	</td>
			</tr>
		</tbody>
		</c:forEach>
	
	</c:when>
</c:choose>
</table>
<br><input type="submit">
</form>
</body>
</html>
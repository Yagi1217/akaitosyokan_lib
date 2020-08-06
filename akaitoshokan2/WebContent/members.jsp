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
	<a href="menu.html">メニューへ</a>
	<h1>会員一覧</h1>
	<br>
	<form action="/akaitoshokan/MembersController" method="post">
		<select name="searchCondition">
			<c:choose>
				<c:when test="${searchCondition eq 'email'}">
					<option value="email" selected>E-Mail</option>
				</c:when>
				<c:otherwise>
					<option value="email">E-Mail</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${searchCondition eq 'id'}">
					<option value="id" selected>ID</option>
				</c:when>
				<c:otherwise>
					<option value="id">ID</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${searchCondition eq 'name'}">
					<option value="name" selected>氏名</option>
				</c:when>
				<c:otherwise>
					<option value="name">氏名</option>
				</c:otherwise>
			</c:choose>


		</select> <input type="text" name="keyword" value="${keyword }"placeholder = "IDは数値のみ入力可
		"> <input
			type="submit" value="検索">
	</form>
	<hr>
	<c:choose>
		<c:when test="${!empty memberList}">
			<table border="1">
				<tbody>
					<tr>
						<td>ID</td>
						<td>氏名</td>
						<td>E-mail</td>
					</tr>
					<tr>
						<td align="center"></td>
						<td align="center"></td>
						<td align="center"></td>
					</tr>
					<c:forEach items="${memberList}" var="memberList">
						<tr>
							<td>${memberList.id }</td>
							<td><a
								href="/akaitoshokan/MemberDetailController?id=${memberList.id }">${memberList.name }</a></td>
							<td>${memberList.email }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${searchResult eq 0}">該当するデータが存在しません。</c:when>
				<c:otherwise>検索フォームに条件を入力し、検索ボタンをクリックしてください。</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</body>
</html>
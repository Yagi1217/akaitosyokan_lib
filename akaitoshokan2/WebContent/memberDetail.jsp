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
	<a href="menu.html">メニューへ</a> -> 
	<a href="members.jsp">会員一覧へ</a>
	<h1>会員詳細</h1>
	<br>
	<table border="1">
		<tbody>
			<tr>
				<td>ID</td>
				<td>氏名</td>
				<td>ふりがな</td>
				<td>E-mail</td>
				<td>住所</td>
				<td>電話番号</td>
				<td>生年月日</td>
				<td>入会年月日</td>
				<td>退会年月日</td>
			</tr>

			<tr>
				<td>${member.id }</td>
				<td>${member.name }</td>
				<td>${member.name_kana }</td>
				<td>${member.email }</td>
				<td>${member.address }</td>
				<td>${member.tel }</td>
				<td>${member.birthday }</td>
				<td>${member.regist_date }</td>
				<td>${member.release_date }</td>
			</tr>
		</tbody>
	</table>
	<h2>貸出中一覧</h2>
	<c:choose>
		<c:when test="${!empty rentalList}">
			<form action="/akaitoshokan/ReturnController" method="post">
				<table border="1">
					<tbody>
						<tr>
							<td>返却</td>
							<td>資料ID</td>
							<td>資料名</td>
							<td>貸出日</td>
							<td>返却期日</td>
						</tr>
					</tbody>
					<tbody>
						<c:forEach items="${rentalList}" var="rentalList">
							<tr>
								<td><label> <input type="checkbox" name="id"
										value=${rentalList.item_id }>
								</label></td>
								<td>${rentalList.item_id }</td>
								<td>${rentalList.name }</td>
								<td>${rentalList.rental_date }</td>
								<td>${rentalList.return_scheduled }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<label style="display: none;"><input type="text"
					name="memberId" value=${member.id }></label> <input type="submit"
					value="返却">
			</form>
		</c:when>
		<c:otherwise>貸出中資料はありません。</c:otherwise>
	</c:choose>

</body>
</html>
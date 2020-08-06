<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ItemList</title>
</head>
<body>

	<%
		String titleName = request.getParameter("titleName");
	%>

	<%-- ↓ローカル環境にて製作中の為↓ --%>

	<%
		String actionUrl = "";
	%>
	<c:choose>
		<c:when test="${param.mode eq 'return' }">
			<%
				actionUrl = "/akaitoshokan/ReturnController";
			%>
		</c:when>
		<c:otherwise>
			<%
				actionUrl = "/akaitoshokan/ItemBorrowingConfirmation";
			%>
		</c:otherwise>
	</c:choose>

	<a href="/akaitoshokan/menu.html">メニューへ</a>

	<div style="margin: 0rem 0.5rem">
		<table>
			<caption>検索フォーム</caption>
			<tbody>
				<form action="/akaitoshokan/ItemList?mode=${param.mode}"
					method="post">
					<tr>
						<td align="right">タイトル名：</td>
						<td><input type="text" name="titleName"
							value="${param.titleName}"></td>
						<td align="right">著者名：</td>
						<td><input type="text" name="writerName"
							value="${param.writerName}"></td>
					</tr>
					<tr>
						<td align="right">分類：</td>
						<td><select name="category">
								<option value=999></option>
								<c:forEach items="${classification}" var="classification">
									<c:choose>
										<c:when test="${classification.code eq param.category}">
											<option value=${classification.code } selected>${classification.name }</option>
										</c:when>
										<c:otherwise>
											<option value=${classification.code }>${classification.name }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
						<td align="right">出版社：</td>
						<td><select name="publisher">
								<option></option>
								<c:forEach items="${publishers}" var="publisher">
									<c:choose>
										<c:when test="${publisher.publisher eq param.publisher}">
											<option value=${publisher.publisher } selected>${publisher.publisher }</option>
										</c:when>
										<c:otherwise>
											<option value=${publisher.publisher }>${publisher.publisher }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="4" align="right"><input type="submit" value="検索"></td>
					</tr>
				</form>
			</tbody>
		</table>
	</div>
	<hr>
	<c:choose>
		<c:when test="${!empty itemLists}">
			<div style="margin: 0rem 0.5rem">
				<table border="1">
					<tbody>
						<form action=<%=actionUrl%> method="post">
							<tr>
								<th></th>
								<th>資料ID</th>
								<th>資料名</th>
								<th>著者名</th>
								<th>出版社名</th>
								<th>返却予定日</th>
							</tr>
							<c:forEach items="${itemLists}" var="itemList">
								<tr>
									<td>
										<%-- 貸し出し中の場合はチェックボックスを出さない(チェックボックスは貸出用のため) --%> <c:choose>
											<c:when
												test="${empty param.mode and !empty itemList.rental_Date }">
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="id" value=${itemList.item_Id }>
											</c:otherwise>
										</c:choose>
									</td>
									<td align="center">${itemList.item_Id }</td>
									<td><a
										href="/akaitoshokan/Item_detail?mode=${param.mode}&itemId=${itemList.item_Id }">${itemList.item_Name }</a>
										<input type="hidden" name="item_Name"
										value=${itemList.item_Name }></td>
									<td>${itemList.writer_Name }</td>
									<td>${itemList.publisher_Name }</td>
									<td><c:choose>
											<c:when test="${empty itemList.rental_Date }">
											貸し出し可能
										</c:when>
											<c:otherwise>
											${itemList.rental_Date }
										</c:otherwise>
										</c:choose></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="6" align="right"><c:choose>
										<c:when test="${param.mode eq 'return' }">
											<input type="submit" value="返却">
										</c:when>
										<c:otherwise>
											<input type="submit" value="貸出">
										</c:otherwise>
									</c:choose></td>
							</tr>
						</form>
					</tbody>
				</table>
			</div>
		</c:when>
		<c:otherwise>検索フォームに条件を入力し、検索ボタンをクリックしてください。</c:otherwise>
	</c:choose>
</body>
</html>
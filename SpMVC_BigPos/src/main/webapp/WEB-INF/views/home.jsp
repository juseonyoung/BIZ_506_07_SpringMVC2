<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>
</head>
<body>
	<table id="poslist">
		<h3>매입매출리스트</h3>
		<tr>
			<th>거래일자</th>
			<th>거래시간</th>
			<th>수량</th>
			<th>매입단가</th>
			<th>매입금액</th>
		</tr>

		<c:choose>
			<c:when test="${empty POS_LIST}">
				<tr>
					<td colspan="5">데이터가 없습니다</td>
				</tr>
			</c:when>

			<c:otherwise>
				<tbody>
					<c:forEach items="${POS_LIST }" var="pos">
						<tr>
							<c:choose>
								<c:when test="${pos.io_input=1}">
									<td>매입</td>
								</c:when>
								<c:when test="${pos.io_input=2}">
									<td>매출</td>
								</c:when>


							</c:choose>

							<td>${pos.io_date}</td>
							<td>${pos.io_time}</td>
							<td>${pos.io_qty}</td>
							<td>${pos.io_price}</td>
							<td>${pos.io_amt}</td>

						</tr>
					</c:forEach>
				</tbody>
			</c:otherwise>
		</c:choose>
	</table>
	<div>
		<button>
			<a href="${rootPath }/input">매입매출등록</a>
		</button>
	</div>
</body>
</html>
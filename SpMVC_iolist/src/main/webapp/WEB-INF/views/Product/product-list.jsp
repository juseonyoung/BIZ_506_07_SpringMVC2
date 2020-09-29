<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<style>
table {
	border-collapse: collapse;
	border: 1px solid #ccc;
	width: 80%;
	margin: 10px auto;
}

th, td {
	border: 1px solid #ccc;
	padding: 8px;
	text-align: left;
}

tbody tr:nth-child(odd) {
	background-color: #f1f1f1;
}

tbody tr:nth-child(even) {
	background-color: #fff;
}

tbody tr:hover {
	background-color: #ddd;
	cousor: pointer;
}

article {
	width: 80%;
	margin: 5px auto;
	text-align: right;
}

article a {
	display: inline-block;
	padding: 8px 16px;
	background-color: navy;
	color: white;
	cursor: pointer;
	text-decoration: none;
	border-radius: 5px;
}

article a:hover {
	background-color: #ddd;
	color: black;
	-webkit-box-shadow: 9px 22px 24px 2px rgba(94, 204, 123, 1);
	-moz-box-shadow: 9px 22px 24px 2px rgba(94, 204, 123, 1);
	box-shadow: 9px 22px 24px 2px rgba(94, 204, 123, 1);
}
</style>
<!-- <script>

	$(function(){
		
		$(".dept_item").click(function(){
			
			
			const d_code = $(this).children().eq(0).text() //리스트에 상품정보 보일 때 alert 가져옴
		
			document.location.href=
				"${rootPath}/dept/detail?id="+d_code //p_code보다는 id나 시퀀스 쓰는 것이 더 좋음 
						// ?id=P00001 형식으로 주소창에 뜸
		})
	})

</script> -->
<table>

	<thead>
		<tr>
			<th>No.</th>
			<th>일자</th>
			<th>시각</th>
			<th>상품명</th>
			<th>구분</th>
			<th>단가</th>
			<th>수량</th>
			<th>합계</th>
		</tr>


	</thead>

	<tbody>
		<c:forEach items="${LIST}" var="VO">
			<!-- VO요소 하나하나 담아서 product item 에 넣음 -->
			<%@ include file="/WEB-INF/views/Product/product-item.jsp"%>
		</c:forEach>

		<tr class="pro_item" data-pcode=${LIST.io_pname}>
			<td>${i.count}</td>
			<td>${LIST.io_date}</td>
			<td>${LIST.io_time}</td>
			<td>${LIST.io_pname}</td>
			<td>${LIST.io_input}</td>
			<td>${LIST.io_price}</td>
			<td>${LIST.io_quan}</td>
			<td>${LIST.io_total}</td>

		</tr>
	</tbody>


</table>

<article>
	<a href="${rootPath}/list">거래처추가</a>

</article>
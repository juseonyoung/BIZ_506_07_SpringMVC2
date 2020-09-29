<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Loo9 매입매출 시스템</title>
<link href="${rootPath}/static/css/index.css?ver=2020-09-29" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
	// js 파일에서 el tag의 ${rootPath} 값을 참조하기 위해서 
	// rootPath변수를 전역으로 선언해 준다

	var rootPath = "${rootPath}"
</script>

</head>
<body>
<header>
	<h3>Loo9 SHOP 매입매출 관리대장💫</h3>
</header>
	<table id="io-table">
		<th>No.</th>
		<th>일자</th>
		<th>시각</th>
		<th>상품명</th>
		<th>매입단가</th>
		<th>판매단가</th>
		<th>수량</th>
		<th>매입합계</th>
		<th>판매합계</th>
		
		<c:choose>
            <c:when test="${empty LISTS}">
               <tr>
                  <td colspan="8">데이터가 없음</td>
               </tr>
            </c:when>
            <c:otherwise>
               <c:forEach items="${LIST}" var="list" varStatus="i">
                  <tr>
                     <td>${i.count}</td>
                     <td>${LIST.io_date}</td>
                     <td>${LIST.io_time}</td>
                     <td>${LIST.io_pname}</td>
                     <td>${LIST.io_input}</td>
                     <td>${LIST.io_price}</td>
                     <td>${LIST.io_quan}</td>
                     <td>${LIST.io_total}</td>
                  </tr>
   
               </c:forEach>
            

            </c:otherwise>

         </c:choose>


</tr>
	</table>

	<div>
		<button id="io-input">입력하기</button>
		<button id="io-look">자세히보기</button>
		<button id="io-update">수정하기</button>
		<button id="io-delete">삭제하기</button>
		
	</div>
	
	<footer>
		<address>CopyRight &copy; ssyy0622@gmail.com</address>
	</footer>
</body>
</html>
</style>

</head>

</html>
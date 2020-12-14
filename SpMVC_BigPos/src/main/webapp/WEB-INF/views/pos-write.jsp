<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>
</head>
<body>
	<h3>매입매출등록</h3>
	
	<form method="POST">
		
		<div>
			<label>거래일</label>
			<input name="io_time" value="${POS.io_time }"/>
		</div>
		
		<div>
			<label>거래시각</label>
			<input name="io_date" value="${POS.io_date }"/>
		</div>
		
		<div>
			<label>수량</label>
			<input name="io_qty" value="${POS.io_qty }"/>
		</div>
		
		<div>
			<label>단가</label>
			<input name="io_price" value="${POS.io_price }"/>
		</div>
		
		<div>
			<label>금액</label>
			<input name="io_amt" value="${POS.io_amt }"/>
		</div>
		
	
		
		
		
		
		
		
		<div>
			<button>저장</button>
		</div>
	</form>
</body>
</html>
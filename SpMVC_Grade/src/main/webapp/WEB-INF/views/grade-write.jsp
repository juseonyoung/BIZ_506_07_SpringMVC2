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
<style>
h3 {
text-align: center;
}
	form div {
		width:80%;
		margin:10px auto;
	}
	
	form label, form input {
		display: inline-block;
		margin:5px 3px;
		padding:5px;
	}
	
	form label {
		width:30%;
		text-align: right;
	}
	
	form input {
		width:60%;
	}
	
	form div:last-child {
		text-align: right;
	}
	
</style>
</head>
<body>
	<h3>성적을 입력하세요</h3>
	
	<form method="POST" >
		<div>
			<label>학번</label>
			<input name="g_id" value="${GRADE.g_id }"/>
		</div>
		
		<div>
			<label>이름</label>
			<input name="g_name" value="${GRADE.g_name }"/>
		</div>
		
		<div>
			<label>국어</label>
			<input name="g_kor" value="${GRADE.g_kor }"/>
		</div>
		
		
		<div>
			<label>영어</label>
			<input name="g_eng" value="${GRADE.g_eng }"/>
		</div>
		
		
		<div>
			<label>수학</label>
			<input name="g_math" value="${GRADE.g_math }"/>
		</div>
		
		
		
		
		<div>
			<button>저장</button>
		</div>
		
		
		
	</form>
	
	
</body>
</html>
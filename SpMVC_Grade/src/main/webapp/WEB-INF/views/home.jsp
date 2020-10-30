<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적처리</title>
<style>
h3 {
	text-align: center;
}

table#gradelist {
	width: 80%;
	margin: 10px auto;
	border-collapse: collapse;
	border: 1px solid #ccc;
}

td, td {
	border: 1px solid #ccc;
}

div#button a{
text-align: center;
}
</style>
</head>
<body>
	<table id="gradelist">
		<h3>성적처리</h3>
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>국어</th>
			<th>영어</th>
			<th>수학</th>
			<th>총점</th>
			<th>평균</th>
			<th>기타</th>

		</tr>

		<c:if test="${empty GRADE_LIST }">
			<tr>
				<td colspan="8">데이터가 없습니다</td>
			</tr>
		</c:if>

		<c:forEach items="${GRADE_LIST}" var="grade">
			<tr>
				<td>${grade.g_id }</td>
				<td><a href="${rootPath }/update?seq=${grade.seq}">${grade.g_name }</a></td>
				<td>${grade.g_kor }</td>
				<td>${grade.g_eng }</td>
				<td>${grade.g_math }</td>
				<td>${grade.g_sum }</td>
				<td>${grade.g_avg }</td>
				<td><a href="${rootPath }/delete?seq=${grade.seq}">삭제</a></td>
			
			</tr>
		</c:forEach>


	</table>

	<div>
		<button><a href="${rootPath }/input">성적입력 하러가기</a></button>
	</div>
</body>
</html>
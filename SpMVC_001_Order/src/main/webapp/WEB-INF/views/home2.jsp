<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  특수문자는 화면을 읽는 게 인식을 하지 못하므로 접근성 테스트에서 통과하지 못한다.
	  따라서 웬만하면 특수문자 쓰지 말것
 -->
<title>나의 홈페이지</title>
<!--  이 파일은 stylesheet구조다.요즘에는 type을 사용하지 않지만 어떤 곳은 명시 해야만 해야되기도 한다. -->
<!--  내가 어제 navi와 header부분 디자인 만들어 놓은 것을 그대로 불러와서 적용하는 것. -->
<link rel ="stylesheet" href = "resources/css/main.css" type ="text/css" >
</head>
<body>
	<%@ include file = "/WEB-INF/views/include/include-header.jspf" %>
	<%@ include file= "/WEB-INF/views/include/include-nav.jspf" %>
	
	<section>
	<h3>반갑습니다</h3>
	<p>나는 ${page} 입니다.</p>
	
	</section>
	<section>
		<img src ="resources/images/turkey.jpg" alt ="터키 사진" width="300px" >
		
	
	</section>	
	
	<footer>
		<address>CopyRight &copy; sinsin09022@gamil.com</address>
	</footer>
	
	<p>서버의 현재 시각 ${serverTime}</p>
</body>
</html>
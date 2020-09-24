<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Read Book 2020 책을 읽자</title>
    <link href="${rootPath}/static/css/index.css?ver=2020-09-24" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script>
    	// js 파일에서 el tag의 ${rootPath} 값을 참조하기 위해서 
    	// rootPath변수를 전역으로 선언해 준다
    	
    	var rootPath = "${rootPath}"
    </script>
    <script src="${rootPath}/static/js/main-nav.js?ver=2020-09-24"></script>
   
  </head>
  <body>
    <header>
      <h1>Read Book</h1>
      <h5>책 속에 길이 있다네..</h5>
    </header>

    <nav id="main-nav">
      <ul>
        <li>Read Book</li>
        <li>HOME</li>
        <li>도서정보</li>
        <li>독서록</li>
        <li>네이버 검색</li>
        <li>회원가입</li>
        <li>로그인</li>
        <li>마이페이지</li>
        <li>로그아웃</li>
      </ul>
    </nav>
    <section id="main-section">
    	<c:choose>
    		<c:when test="${BODY =='BOOK-LIST'}">
    			<%@ include file ="/WEB-INF/views/books/book-list.jsp" %>
    		</c:when>
    		<c:when test="${BODY =='BOOK-WRITE'}">
    			<%@ include file ="/WEB-INF/views/books/book-write.jsp" %>
    		</c:when>
    		<c:when test="${BODY =='BOOK-DETAIL'}">
    			<%@ include file ="/WEB-INF/views/books/book-detail.jsp" %>
    		</c:when>
    	</c:choose>
    
    	</section>
    <footer>
      <address>CopyRight &copy; seonyoung :)</address>
    </footer>
  </body>
</html>

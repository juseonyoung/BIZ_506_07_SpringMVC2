<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<c:set var="rootPath" value="${pageContext.request.contextPath}" />
  
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<link rel="stylesheet" type="text/css" href="${rootPath}/static/css/input.css"/>
<style>
	
</style>

</head>
<body>
<!--  서버로 데이터를 전송하기 위한 부분
	디자인 적인 부분은 css파일로 옮겨놓음
 -->
	    <%@ include file="/WEB-INF/views/include/include-header.jspf" %>
    <section id="main">
    <!-- action="wirter" : input에 적은 내용을 전달 받을 곳 지정하는 것 -->
    <form action="${rootPath}/blog/writer" method="POST">
    <div>
    	<input name="title" placeholder="제목을 입력하세요"/>
    </div>
    <div>
    <!--우리가 각 각의 input에 입력을 하면 각 각 전달 받을 수 있도록 나눠서 name을 지정해줌
    	사용자에게 내용을 입력받을 부분. 그리고 이 내용이 Controller에간다
    	그러면 SpringFrameWork가 알아서 해준다. 우리는 각각 연결 지을 때 변수명을 제대로 
    	써주기만 하면 된다.
    -->
    	<input name="content" placeholder="내용을 입력하세요"/>
    </div>
    <div>
    	<input name="user" placeholder="사용자를 입력하세요"/>
    </div>
    <div>
    	<button type="button">처음으로</button>
    	<!-- type="submit : 기본값 -->
    	<!--  submit로 써주지 않으면 우리가 작성한 글을 전송하지 않기 때문에 
    	꼭 submit button을 사용해야 한다. -->
    	<button type="submit">저장</button>
    </div>
    </form>
	</section>
<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>

</body>
</html>
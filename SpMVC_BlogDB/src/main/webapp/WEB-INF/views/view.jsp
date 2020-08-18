<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<c:set var ="rootPath" value="${pageContext.request.contextPath }"/>
<html>
<head>
<!-- home.jsp에 있는 코드 붙여 넣음. -->
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
</head>
	<style>
	
		menu {
		text-align:right;
		width:90%;
		}
		
		menu a{
			display:inline-block;
			padding:8px 16px;
			margin:5px;
			border-radius:5px;
			
		}
		
		menu a:hover{
		background-color:gray;
		color:blue;
		
		}
		
		menu a:nth-child(1){
		
			background-color:aqua;
		}
		
		
		menu a:nth-child(2){
		
			background-color:yellowgreen;
		}
		
		menu a:nth-child(3){
		
			background-color:orange;
		}
		
		menu a:nth-child(4){
		
			background-color:gold;
		}
		
		
		
	</style>
<body>
	    <%@ include file="/WEB-INF/views/include/include-header.jspf" %>
    <section id="main">
       
   
        <!-- 이름명 명명할 때 가급적 _(언더바)로 해주기
        나중에 자바 스크립으로 넘어가면 -(일반)로 명명했을 때 문제 생길 수도 있다. -->
        <article id="blog_body">
            <section class="blog_title">
                <h3>${BLOG.bl_title}</h3>
                <h5>작성자 :${BLOG.bl_user}</h5>
                <h5>작성일자 :${BLOG.bl_date}</h5>
                <h5>작성시각:${BLOG.bl_time}</h5>
                
               
            </section>
            
            
            <section class="blog_text">
                <p>${BLOG.bl_contents}</p>
            </section>
              
            </article>
            
            <menu>
            
            	<a href="${rootPath}/">처음으로</a>
            	<a href="${rootPath}/blog/list">리스트</a>
            	<a href="${rootPath}/blog/update?seq=${BLOG.bl_seq}">수정</a>
            	<a href="javascript:void(0)" onclick="goDelete()">삭제</a>
            	
            	
            </menu>
            
       
<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
	<script>
		function goDelete(){
			if(confirm("정말 삭제할까요?")){
				document.location.href="${rootPath}/blog/delete?seq=${BLOG.bl_seq}"
			}
			
		}
	</script>

</body>
</html>
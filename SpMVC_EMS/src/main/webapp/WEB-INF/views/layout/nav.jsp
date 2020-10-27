<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    
<script>
	
	document.addEventListener("DOMContentLoaded",function(){
		
		document.querySelector("#nav-ems").addEventListener("click",function(){
			document.location.href="${rootPath}/ems/list"
		})
	})
	
	
	
	
</script>
<nav>
	<ul>
		
		<li id="nav-ems">받은메일함</li>
		<li id="nav-wems">메일쓰기</li>
	</ul>
</nav>
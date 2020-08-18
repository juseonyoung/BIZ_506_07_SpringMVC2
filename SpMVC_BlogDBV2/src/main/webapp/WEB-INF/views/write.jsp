<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf"%>
<!-- css값 바꾸면 뒤에 ver=2020-08-15해줘야 한다. -->
<link rel="stylesheet" type="text/css"
	href="${rootPath}/static/css/input.css?ver=2020-08-15" />
<script>
	$(function(){
	
		// 저장버튼의 type ="button"으로 설정하여 submit기능을 무력화하고 jq에서 버튼이 클릭됐을 때 
		// 실행할 이벤트 핸들러를 만들고 input box에 값이 있는지 검사하여 없으면 alert을 보이고 입력받도록 수행한다. 
		// input tag에 입력된 값 추출하기
		$("#save").click(function(){
			
		
			var user = $("#bl_user").val()	
			var title = $("#bl_title").val()
			var contents = $("#bl_contents").val()
			
			if(user ==""){
				alert("작성자 이름은 반드시 입력해야 합니다")
				
				$("#bl_user").focus()
				return false;
			}
			if(title==""){
				alert("제목은 반드시 입력해야 합니다")
				
				$("#bl_title").focus()
				return false;
			}
			if(contents==""){
				alert("내용은 반드시 입력해야 합니다")
				
				$("#bl_contents").focus()
				return false;
			
			
			}
			// form(input)에 입력된 데이터를 서버로 전송하라 
			$("form").submit()
		})
		
		$("#goHome").click(function(){
			document.location.href = "${rootPath}/blog/list"
		})
	
		
	})
	
</script>
</head>
<body>
	<!--  서버로 데이터를 전송하기 위한 부분
	디자인 적인 부분은 css파일로 옮겨놓음
 -->
	<%@ include file="/WEB-INF/views/include/include-header.jspf"%>
	<section id="main">
		<!-- action="wirter" : input에 적은 내용을 전달 받을 곳 지정하는 것 -->
		<form method="POST">
			<input name="bl_seq" value="${BLOG.bl_seq}" type="hidden" /> <input
				name="bl_date" value="${BLOG.bl_date}" type="hidden" /> <input
				name="bl_time" value="${BLOG.bl_time}" type="hidden" />


			<div>
				<input name="bl_user" id="bl_user" value="${BLOG.bl_user}"
					placeholder="사용자를 입력하세요" />
			</div>

			<div>
				<input name="bl_title" id="bl_title" value="${BLOG.bl_title}"
					placeholder="제목을 입력하세요" />
			</div>
			<div>
				<!--우리가 각 각의 input에 입력을 하면 각 각 전달 받을 수 있도록 나눠서 name을 지정해줌
    	사용자에게 내용을 입력받을 부분. 그리고 이 내용이 Controller에간다
    	그러면 SpringFrameWork가 알아서 해준다. 우리는 각각 연결 지을 때 변수명을 제대로 
    	써주기만 하면 된다.
    -->
				<input name="bl_contents" id="bl_contents"
					value="${BLOG.bl_contents}" placeholder="내용을 입력하세요" />
			</div>

			<div>
				<button type="button" id="goHome">처음으로</button>
				<!-- type="submit : 기본값 -->
				<!--  submit로 써주지 않으면 우리가 작성한 글을 전송하지 않기 때문에 
    	꼭 submit button을 사용해야 한다. -->
				<button type="button" id="save">저장</button>
			</div>
		</form>
	</section>
	<%@ include file="/WEB-INF/views/include/include-footer.jspf"%>
	
</body>
</html>
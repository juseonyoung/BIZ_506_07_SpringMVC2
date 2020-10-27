<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    
<style>
</style>
<script>

	$(function(){
		var toolbar = [ [ 'style', [ 'bold', 'italic', 'underline' ] ],
			[ 'fontsize', [ 'fontsize' ] ],
			[ 'font Style', [ 'fontname' ] ], [ 'color', [ 'color' ] ],
			[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
			[ 'height', [ 'height' ] ], [ 'table', [ 'table' ] ],
			[ 'insert', [ 'link', 'hr', 'picture' ] ],
			[ 'view', [ 'fullscreen', 'codeview' ] ]

	]
		
		$("#s_content").summernote({
			lang : "ko-KR",
			width : "80%",
			height : "200px",
			toolbar : toolbar
		});
		
		
	})
	
</script>

<form id ="write-form" method="POST" enctype="multipart/form-data">
	<fieldset>
		<legend>새 메일</legend>
		<div>
			<label>받는사람</label><input name="to_email">
		</div>
	
	<div>
			<label>제목</label><input name="s_subject">
		</div>
	
	<div>
			<label></label>
			<textarea id="s_content" rows="5" cols="20" name="s_content"></textarea>
		</div>
		
		<div>
			<label>이미지</label> <input type="file" name="file" accept="image/*" >
		</div> 
		
		<div>
			<label>멀티이미지</label> <input type="file" name="files" accept="image/*" 
			multiple="multiple" >
		</div> 
		
		<div class="button-box">
			<button type="button" id="list">보낸메일함</button>
			<button type="submit" id="save">보내기</button>

		</div>
		
		
	</fieldset>

</form>













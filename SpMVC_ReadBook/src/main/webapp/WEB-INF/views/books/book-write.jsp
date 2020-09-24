<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link href="${rootPath}/static/css/book-write.css?ver=2020-09-24-001" rel="stylesheet">

<script src="${rootPath}/static/js/book-write.js?ver=2020-09-24-006"></script>

	<h3>도서 정보등록</h3>
	<form:form id="books" modelAttribute="bookVO">
		<fieldset>
			<legend>도서정보 입력</legend>
		
			<div><lable for="title">도서명</lable>
			<!-- path 값 보내는 데 필수, id 써주면 편하다 -->
			<form:input path="title"  placeholder="도서명" />
			<button id="naver-search" type="button">네이버 검색</button>
			</div>
			<!-- 눌럿을때 submit실행되지 말라고 type을 버튼으로 함 -->
			
			<div><lable for="link">상세링크</lable>
			<form:input path="link"  placeholder="상세링크" />
				</div>
			
			<div><lable for="image">이미지</lable> 
			<form:input path="image" placeholder="이미지" />
				</div>
				
			<div><lable for="author">저자</lable> 
			<form:input path="author"  placeholder="저자" />
				</div>
				
			<div><lable for="price">도서정가</lable>
			 <form:input path="price" placeholder="가격" />
			 	</div>
			 	
			 <div><lable for="discount">할인가격</lable> 
			 <form:input path="discount"  placeholder="할인" />
			 	</div>
			 	
			 <div><lable for="publisher">출판사</lable> 
			 <form:input path="publisher" placeholder="출판사" />
			 	</div>
			 	
			 <div><lable for="isbn">ISBN</lable>
			  <form:input path="isbn" placeholder="ISBN" />
			  	</div>
			  	
			  <div><lable for="description">세부설명</lable> 
			  <form:input path="description" placeholder="세부설명" />
			  	</div>
			  	
			  <div><lable for="pubdate">출간일자</lable> 
			  <form:input path="pubdate" placeholder="출간일자" />
			  	</div>
			  	
			  <div><lable for="buydate">구입일자</lable>
			<form:input path="buydate"  placeholder="구입일자" />
				</div>
				
			<div><lable for="buyprice">구입금액</lable> 
			<form:input path="buyprice"  placeholder="구입금액" />
				</div>
				
			<div><lable for="buystore">구입처</lable>
			 <form:input path="buystore" placeholder="구입처" />
			 	</div>
			 	
			<div class="button-box">
				<button id="btn-save" type="button">저장</button>
			</div>
		</fieldset>

	</form:form>
	
	<section id="book-modal">
		<article id="modal-body">
	<div id="modal-header">
		<span>&times;</span>
	</div>
	
		<div id="search-result"></div>
		</article>
	</section>
	









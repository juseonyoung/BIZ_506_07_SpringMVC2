<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>
</head>
<body>
	<style>
	
	* {
		box-sizing:border-box;
		padding:0;
		margin:0;
		
	}

	html, body {
		width:100%;
		height:100%;
	}
	
	body {
	
		overflow:auto;
	}


	
form#books {
	width: 95%;
	margin: 10px auto;
}

form#books input {
	width: 90%;
	display: inline-block;
	/*만약 input 박스와 버튼 등 다른 태그를 한줄에 나란히 놓으면서 인풋 박스의 width를
			임의로 설정하고 싶으면 display를 인라인-블록으로 설정
			그냥 블록으로 설정하면 width는 임의로 설정할 수 있지만 다른 태그를 오른쪽에 위치하게 할 수 없다. 
			기본값인 inline 이면 width 를 설정할 수 없다. */
	border: 1px solid #ddd;
	margin: 5px;
	padding: 8px;
}

form#books fieldset {
	border: 1px solid rgb(0, 100, 200);
	border-radius: 10px;
}

form#books #title {
	width: 70%;
}

form#books div.button-box {
	width: 93%;
	text-align: right;
}

form#books button {
	border: none;
	outline:0;
	padding:0.5ren 12px;
	border-radius:5px;
}

form#books button#naver-search {
	background-color: #33FF77;
	color:black;
	padding:0.4rem;
}

form#books button#btn-save {
	background-color: hotpink;
	color:black;
	padding:0.4rem;
	display:inline-block;
}

form#books button:hover {

	box-shadow: 5px 5px 5px rgba(0,0,0,0.3);
}

/* legend {
	text-align:center;
} */

/*모달설정-------------------------------------------------*/
section#book-modal {
	position: fixed;
	top:0;
	left:0;
	background-color: rgba(0,0,0,0.4) !important; /*색상 지정했을 때 다른 css하고 충돌하여 색상지정이 ㅇ잘 안되면 important */
	width:100%;
	height:100%;

	}
	
	article#modal-body {
	position: absolute;
	top:50%;
	left:70%;
	width:70%;
	height:50%;
	transform:translate(-50%,-50%);
	
	display:flex;
	flex-flow: column nowrap;
	}
	
	div#modal-header{
		flex:1;
		width:60%;
		text-align:right;
		background-color: rgba(100,200,200,0.5);
		
	}
	
	div#modal-header span{
		font-size:30px;
		font-weight:500;
		color:white;
		cursor:pointer;
		margin:15px;
	}
	
	div#modal-header span:hover{
		color:red;
	}
	
	div#search-result{
	flex:7;
	background-color: rgba(255,255,255,1);
	width:60%;
	overflow:auto;
	border:2px solid rgba(0,0,0,1);
	overflow:auto;
	padding:30px;
	
	border-bottom-left-radius:15px;
	border-bottom-right-radius:15px;
	
	box-shadow: 10px 10px 10px rgba(0,0,0,0.5);
	
	}
	
	section#book-modal article{
		height:70%;
		width:60%;
		box-shadow:10px 10px 10px rgba(0,0,0,0.5);
	}
	
		
	

</style>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(function(){
		$("#naver-search").click(function(){
			
			let title =$("#title").val()
				if(title ===""){
					alert("도서명을 입력한 후 검색하세요")
					$("#title").focus()
					return false
				}
			// ajax 사용하여 서버에 네이버 검색 요청
			$.ajax({
				// ajax로 서버의 /naver/search URL에 POST로 요청을 하면서
				// search_text 변수에 title 변수에 담긴 값을 담아 전달하고 
			
				url : "${rootPath}/naver/search",
				method:"POST",
				data:{"search_text": title},
				// 서버가 데이터 조회를 수행한 후 view(HTML코드) 코드를 return 하면 그 view코드를
				// 결과를 #search-result div box에 채워서 보여달라 
				success: function(result){
					$("#search-result").html(result) //전체를 다 보이지말고 박스 안에 보여라 
					// 뷰파일을 result 박스에 html형식으로 overflow:auto 박스 넘치면 스크롤 생기게 해라 
				},
				error: function(error){
					alert("서버 통신 오류!")
				}
			})
			
			$("section#book-modal").css("display","flex")
			
		})
		
		// x 표시 클릭했을 때 모달 창 닫기 
		$("div#modal-header span").click(function(){
			$("#book-modal").css("display","none")
		})
		
		//동적으로 구현된 HTML에 이벤트 핸들링 설정하기 
		// 현재 dectment가 생성되는 동안에 없던 태그를 JS코드에서 동적으로 생성햇을 경우
		// 화면이 그려지는 것은 아무 문제가 없으나
		
		//  JS에서 이벤트핸들러를 설정할때 아직화면에 없는 ㄴ태그를 연결하면 무시해버리고
		// 사후에(html 문서완성후) JS  코드로 생성할 태그(is, class)이벤트를 설정하려면 자체에 설정하지 않고
		// 가장 상위 객체인 document에 on 함수를 사용하여 이벤트를 설정한다. 
		// $(document)on("event","대상",function(){} 이런 형식으로... 
				
				
		//주의사항
		//$(selector).click(function(){
			//만약 기존에 selector에 click event가 설정되어있으면 기존의 이벤트를 덮어쓰기 한다
			
		//$(document).on("event","selector")
		// 만약 기존에 selector에 대한 click event가 설정되어 있더라도 중복정의 된다. 
		// 동적으로 여는곳에서는 $(document).on() 사용하여 event핸들러 설정
		// 동적으로 열리는 곳에서 절대 사용하면 안된다. 
		// 동적으로 열리는 곳에서는 $(selector).click() 를 사용하자 
		
		 $(document).on("click","div.book-select",function(){ //문서 전체에 event 걸어버림 
			let isbn = $(this).data("isbn")
			
			
			//13자리 isbn추출
			// 코드의 오른쪽에서 13자리를 잘라내라 
			let length = isbn.length
			isbn = isbn.substring(length-13)
			alert(isbn)
			
			
			$.ajax({
				url : "${rootPath}/api/isbn",
				method : "POST",
				data :{"search_text": isbn}
			
				})
				.done(function(bookVO){
					//alert(JSON.stringify(bookVO)) //가져온 데이터를 input에 쏟아부어
					$("#seq").val(bookVO.seq);
					$("#title").val(bookVO.title);
					$("#link").val(bookVO.link);
					$("#image").val(bookVO.image);
					$("#author").val(bookVO.author);
					$("#price").val(bookVO.price);
					$("#discount").val(bookVO.discount);
					$("#publisher").val(bookVO.publisher);
					$("#isbn").val(bookVO.isbn);
					$("#description").val(bookVO.description);
					$("#pubdate").val(bookVO.pubdate);
					$("#buydate").val(bookVO.buydate);
					$("#buyprice").val(bookVO.buyprice);
					$("#buystore").val(bookVO.buystore);
					$("#section$book-modal").css("display","none")
					
				})
				.fail(function(xhr,textStatus, error){
					alert("서버와 통신오류")
				})
		}) 
		$("section#book-modal").css("display","none")
	})
</script>

	<h3>도서 정보등록</h3>
	<form method="POST" id="books">
		<fieldset>
			<legend>도서정보 입력</legend>
			<input name="seq" id="seq" placeholder="일련번호" />
			<!-- name 값 보내는 데 필수, id 써주면 편하다 -->
			<input name="title" id="title" placeholder="도서명" />
			<button id="naver-search" type="button">네이버 검색</button>
			<!-- 눌럿을때 submit실행되지 말라고 type을 버튼으로 함 -->
			<input name="link" id="link" placeholder="상세링크" /> 
			<input name="image" id="image" placeholder="이미지" /> 
			<input name="author" id="author" placeholder="저자" />
			 <input name="price" id="price" placeholder="가격" /> 
			 <input name="discount" id="discount" placeholder="할인" /> 
			 <input name="publisher" id="publisher" placeholder="출판사" />
			  <input name="isbn" id="isbn" placeholder="ISBN" /> 
			  <input name="description" id="description" placeholder="세부설명" /> 
			  <input name="pubdate" id="pubdate" placeholder="출간일자" />
			<input name="buydate" id="buydate" placeholder="구입일자" /> 
			<input name="buyprice" id="buyprice" placeholder="구입금액" />
			 <input name="buystore" id="buystore" placeholder="구입처" />
			<div class="button-box">
				<button id="btn-save" type="button">저장</button>
			</div>
		</fieldset>

	</form>
	
	<section id="book-modal">
		<article id="modal-body">
	<div id="modal-header">
		<span>&times;</span>
	</div>
	
		<div id="search-result"></div>
		</article>
	</section>
	
</body>
</html>










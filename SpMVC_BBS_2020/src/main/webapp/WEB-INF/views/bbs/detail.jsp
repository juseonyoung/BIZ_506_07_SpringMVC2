<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}

h3 {
	margin: 20px auto;
	text-align: center;
	background-image: url("${rootPath}/static/image/냥이.jpg");
	padding: 4.5rem;
}

table.bbs-detail {
	border: 1px solid blue;
	margin: 20px auto;
	display: flex;
	flex-direction: column;
	align-items: center;
	padding: 10px;
	width: 70%;
}

table.bbs-detail th, table.bbs-detail tr td {
	text-align: center;
}

table.bbs-detail th {
	padding: 10px;
	background: linear-gradient(to right, #ff105f, #ffad06);
}

table.bbs-detail tr td {
	padding: 7px;
}

section#bbs-button-box {
	width: 50%;
	margin: 10px auto;
	padding: 6px 12px;
	text-align: right;
}

section#bbs-button-box button {
	margin: 5px;
	padding: 10px 16px;
	border: 0;
	outline: 0;
	border-radius: 5px;
	font-weight: bold;
}

section#bbs-button-box button:hover {
	box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.6);
}

section#bbs-button-box button:nth-child(1) {
	background-color: white;
	border: 1px solid black;
}

section#bbs-button-box button:nth-child(2) {
	background-color: white;
	border: 1px solid black;
}

section#bbs-button-box button:nth-child(3) {
	background-color: navy;
	color: white;
}
</style>

<script>
	document.addEventListener("DOMContentLoaded", function() {
		document.querySelector("section#bbs-button-box").addEventListener("click",function(e){
			
			let url = "${rootPath}/bbs/${BBSVO.b_seq}/"
			
			if(e.target.tagName ===('BUTTON')){
				
				// 게시글 삭제를 요청하면(삭제버튼 클릭)
				// ajax를 통해 서버에 delete method타입으로 삭제를 요청한다. 
				if(e.target.className == "delete"){
					if(!confirm("정말 삭제할까요?")){
						
						let data = {seq:"${BBSVO.b_seq}"}
						fetch("${rootPath}/api/bbs", 
		
								{method : "DELETE", 
								headers : {
								
									"Content-Type": "application/json"
								},
								body : JSON.stringify(data) // JSON 객체데이터를 문자열화 하여 HTTP Body에 담는다
							}
						)
						.then(function(result){
							alert("성공")
						})
						.catch(function(error){
							alert("실패")
						})
					
						return false;
					}
				}
				document.location.href=url + e.target.className
			}
		})
	})
</script>
<body>
	<h3>Detail View</h3>
	<section>

		<table class="bbs-detail">
			<th>작성일시</th>
			<th>작성시각</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회수</th>
			<tr>
				<td>${BBSVO.b_date }</td>
				<td>${BBSVO.b_time }</td>
				<td>${BBSVO.b_writer }</td>
				<td>${BBSVO.b_subject }</td>
				<td>${BBSVO.b_count }</td>
			</tr>
		</table>
		<table class="bbs-detail">
			<th colspan="3">내용</th>

			<th>사진</th>

			<tr>
				<td colspan="3">${BBSVO.b_content }</td>
				<td></td>
				<td></td>
				<td><a href=" ${rootPath}/upload/${BBSVO.b_file }" target=_new> <img src="${rootPath}/upload/${BBSVO.b_file }" width="200px">
				</a></td>
			</tr>
		</table>

	</section>
	<section id="bbs-button-box">
		<button class="list">리스트</button>
		<button class="update">수정</button>
		<button class="delete">삭제</button>

	</section>
</body>












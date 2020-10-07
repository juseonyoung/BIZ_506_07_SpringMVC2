<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${rootPath}/static/css/member-write.css?ver=2020-09-28 " />

<script>
	// 함수를 변수처럼 선언하는 ES6 코드
	// 함수를 변수처럼 선언하면 = JS 객체화 된다
	// 함수 = 객체 = 변수
	// 1급 함수 : 함수자체를 다른함수의 매개변수로 전달할 수 있다.

	const password_check = function() {

		let username = $("#username").val()
		let password = $("#password").val()

		if (password === "") {
			$("div#msg_password").css("display", "block")
			$("div#msg_password span").css("color", "orange")
			$("div#msg_password span").text("회원 정보를 수정하려면 비밀번호를 입력하세요")
			$("#password").focus()
			return false;

		}

		$.ajax({
			url : "${rootPath}/member/password_check",
			method : "POST",
			data : {
				"username" : username,
				"password" : password
			},
			beforeSend : function(ax) {
				ax.setRequestHeader("${_csrf.headerName}", "${_csrf.token}")
			},
			success : function(result) {

				$("div#msg_password").css("display", "block")
				if (result === "FAIL") {
					//alert("사용가능한 username입니다")
					$("div#msg_password span").text("비밀번호가 일치하지 않습니다!")
					$("div#msg_password span").css("color", "blue")
					$("#password").focus() // 사용가능하면 비밀번호 누르는 데로 포커스 이동해라 커서가 거기로 바뀜
					return false
				} else {
					$("div#msg_password").css("display", "none")
					$("form").submit()
				}
			},
			error : function() {
				alert("서버 통신오류 입니다!")
			}

		})
	}

	$(function() {

		$("#btn_save").click(function() {
			password_check()
				
		})
	})
	
</script>
<style>
div#msg_username, div#msg_password {
	display: none;
}
</style>

<!-- 컨트롤러에서 @sessionattri와 @modelattribute() 설정이 있고 jsp 에서 spring form taglib을 사용하면서
form:form에 modelattribute를 설정해 두면
현재 이 jsp 에는 username, password, re_password만 있지만 memberVO 만들어진 userdetailsvo에 설정된 모든 변수가
마치 input hidden 으로 설정된 것과 같이 포함되어 있다 -->

<form:form modelAttribute="memberVO" id="member-write">
	<!-- modelattribute 설정하여 pro_vo와 p_code 연결하여 간편한 코딩 -->
	<fieldset>
		<!--필드셋과 레전드 붙어다님!! 레전드는 박스이름 필드셋은 박스를 만들어줌-->
		<legend>회원정보 확인</legend>
		<div>
			<label>회원 ID</label>
			<form:input path="username" class="username" readonly="true"/>

		</div>

		<div id="msg_username">
			<label></label> <span></span>
		</div>

		<div>
			<label>비밀번호</label>
			<form:input path="password" type="password" />
		</div>

		<div id="msg_password">
			<label></label> <span></span>
		</div>


		<div id="btn_box">
			<button type="button" id="btn_home">홈으로</button>
			<button type="button" id="btn_save">다음으로</button>
		</div>
	</fieldset>
</form:form>
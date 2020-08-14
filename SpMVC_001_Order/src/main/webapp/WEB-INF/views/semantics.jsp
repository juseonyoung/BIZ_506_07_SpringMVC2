<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  특수문자는 화면을 읽는 게 인식을 하지 못하므로 접근성 테스트에서 통과하지 못한다.
	  따라서 웬만하면 특수문자 쓰지 말것
 -->
<title>나의 홈페이지</title>
</head>
<body>
<!--  include : jsp에서만 사용 할 수 있는 명령어 (꺽쇄 퍼센트 꺽쇄 퍼센트)-->
	<%
	// JSP 코드가 들어가는 영역
	// scriptlet, Java 문법의 스크립트를 HTML 파일에 끼워 넣는 곳
	// 우리가 썼던 자바 코드를 넣어서 프로그래밍 할 수 있다.
	// 디자이너 입장에서는 자바코드는 상당히 불편. 따라서 초창기 spring은 controller가 없고 모든 것이 jsp에서 이루어짐
	// 그런데 이걸 그대로 html가져가서 쓰면 화면이  쏠린다던가 하는 그런 일들ㄹ이 발생해서 디자이너가 이 부분을 막 지워버림 ㅠ
	// 그러니 include file등을 넣을 때만 쓰고
	// jsp코드를 넣는 부분을 원래 자바 주석 다는 것처럼 // 이렇게 써서
	// xml 주석이랑 충돌나지 않게 해라(디자이너들은 디자인 깨지는 것 때문에 웬만하면 주석을 안달기 떄문이다.)
	
	// jspf : 우리가 jsp파일을 만들면 view를 통해 java파일로 만들고 -> *.class로 바꾸어 우리의 화면으로 보여주는 과정
	// 		jspf는 이 파일은 별도로 java파일로 만들지 마라 (개별적으로 컴파일 하지 말아라)
	//		다른 파일에 포함해서 만들 것이기 때문에(home.jsp) 한번만 java파일로 컴파일 하게 하는것
	//		(다 컴파일 하게 만드면) 너무 비효율적. 
			
	String name ="홍길동";
	
	%>
	<%@ include file = "/WEB-INF/views/include/include-header.jspf" %>
	<%@ include file= "/WEB-INF/views/include/include-nav.jspf" %>
	
	
	<%
	/*
	semantics tag(시만텍, 시만틱)
	HTML5에서 div tag를 사용한 layout을 대체하는 새로운 규격의 tag들
	보통 <div id="header"></div>와 같은 element로 layout을 만들어 썼는데
	tag들이 여러번 반복적으로 포함되는 layout구조에서는
	가독성을 심각하게 해치게 되어, 유지보수, 기능추가, 변경 등을 수행하는데
	많은 애로사항이 있다.
	
	이러한 여러가지 불편한 상황을 대체하고자 HTML5에서 새롭게 도입된 tag들
	
	이 tag들은 단어가 내포하는 의미를 잘 이해하고 사용하면 layout의 구조를 파악하는데
	매우 편리하다.
	
	기술적으로 이 tag들은 성질(용도)이 모두 div와 유사하다.
	
	*/
	
	%>
	<section>
	<h3>반갑습니다</h3>
	<p>나는 ${name} 입니다.</p>
	
	</section>
	<section>
		<article>
			<section>
				<article>
				
				</article>
			
			</section> 
		</article>
	</section>
	<div id="section">
		<div id ="article">
			<div id ="sub_section">
				<div id="article_1">
				</div>
			</div>
		</div>
	</div>
		
	
	<p>서버의 현재 시각 ${serverTime}</p>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!--  이 파일은 stylesheet구조다.요즘에는 type을 사용하지 않지만 어떤 곳은 명시 해야만 해야되기도 한다. -->
<!--  내가 어제 navi와 header부분 디자인 만들어 놓은 것을 그대로 불러와서 적용하는 것. -->
<!-- include 사용하는 이유는 우리는 상당부분 article빼고는 다른 부분은 계속 같기 때문에
	그래서 이러한 부분은 다시 만들지 않도록 따로 분리해두는 것
 -->

	<%@ include file = "/WEB-INF/views/include/include-head.jspf" %>
</head>
<body>
	<%@ include file = "/WEB-INF/views/include/include-header.jspf" %>
	<%@ include file= "/WEB-INF/views/include/include-nav.jspf" %>
	
	<section>
	<h3>반갑습니다 &#128131; </h3>
	<p>나는 ${name} 입니다.</br> 방문해주셔서&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	반갑습니다아아아아아아 &#128064; </p>
	<hr/>
	<p id="p1">& nbsp ; 는 Non-Breaking space의 약자로 web page에서 문서를 작성할 때</br>
	단어와 단어 사이에 강제로 빈칸을 추가 하고자 할 때 사용하는 특수문자</br>
	web page에서는 space(공백)문자는 개수에 상관없이 1개만 표시하게 되기 때문에</br>
	단어와 단어사이에 많은 갯수의 빈칸을 넣고 싶으면 & nbsp ; 특수코드를 넣는다.
	
	<hr/>
	<p id="p2">& nbsp ; 는 Non-Breaking space의 약자로 web page에서 문서를 작성할 때</br>
	단어와 단어 사이에 강제로 빈칸을 추가 하고자 할 때 사용하는 특수문자</br>
	web page에서는 space(공백)문자는 개수에 상관없이 1개만 표시하게 되기 때문에</br>
	단어와 단어사이에 많은 갯수의 빈칸을 넣고 싶으면 & nbsp ; 특수코드를 넣는다.
	
	<hr/>
	<p id="p3">& nbsp ; 는 Non-Breaking space의 약자로 web page에서 문서를 작성할 때</br>
	단어와 단어 사이에 강제로 빈칸을 추가 하고자 할 때 사용하는 특수문자</br>
	web page에서는 space(공백)문자는 개수에 상관없이 1개만 표시하게 되기 때문에</br>
	단어와 단어사이에 많은 갯수의 빈칸을 넣고 싶으면 & nbsp ; 특수코드를 넣는다.
	<hr/>
	
	<!-- Pre :  내가 입력한 그대로 출력되도록 하는 tag. 빈칸이랑 다 포함이 된다. -->
	
		<pre>
			우리는 대한민국       사람입니다.
			Republic of Korea
			&nbsp; 우리나라만세~
		</pre>
	</p>
	
	</section>
	<section>
		<img src ="resources/images/turkey.jpg" alt ="터키 사진" width="300px" >
		
	
	</section>	
	<%@ include file ="/WEB-INF/views/include/include-footer.jspf" %>
	<p>서버의 현재 시각 ${serverTime}</p>
</body>
</html>
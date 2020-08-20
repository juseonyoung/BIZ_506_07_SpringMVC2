# Spring Project 빛나리 쇼핑몰 V1
* 상품관리, 거래처관리, 회원가입, 로그인을 포함
* DB는 오라클 사용할 것
* 반응형 메뉴를 사용, 반응형 메인화면 구현!

## Project 시작
* Java Version 1.8 로 변경
* Spring Framework 5.2.8
* lombok, logback
* views/home.jsp 삭제 후 재 생성

## DB 연동설정!(pom.xml)
* String-jdbc
* mybatis
* mybatis-spring
* commons-dbcp2
* ojdbc

*spring/appServlet/mybatis-context.xml 파일 생성, 작성
spring bean 으로 만들어야 함
mybatis bean tx spring 네개 체크하고 각각 끝에 있는 최신 버전으로 

## URL(Uniform Resource Location), URI(Uniform Resource Identifier)
* URL : 파일식별자 (index.html)
* URI : 통합 자원 식별자 (index.jsp)
 
 http: localhost:8080/shop
 원래 구분되어있는데 임의로 그냥 전체를 URL로부름 원칙적으로는 뒤에 파일이름이 붙어야 URL이라고 함
 
### View단에서 사용하는 URL
* <a href ="http://localhost:8080/shop"> 서버 Home</a>
톰캣 WAS 에게 shop이라는 context를 가진 project가 작동되고 있느냐?? 라고 묻는 Request

* <a href ="http://localhost:8080/shop/product/list">상품리스트</a>
shop context의 dispatcher에게 product/list를 수행할 수 있는 controller method가 있느냐?? 라고 묻는 Request

* 이 HTML 코드를 화면에서 만나면 HyperText(anchor 문자열) 를 클릭했을 때 서버에 Request한다. 
 이 때 수행하는 request는 method=RequestMethod.GET 설정된 함수에서 처리한다. 
 
* href : HyperText Reference, URL 주소

### HTML 코드에서 GET method로 Request 요청하는 곳들
* anchor tag : <a href="주소">텍스트</a> DB-Controller에 의해 요청 처리하여 view에서 보여줌 (컨트롤러 거쳐야함)

* 스크립트에서 이렇게 요청하면 document.location.href="주소" 
* 스크립트에서 이렇게 요청하면 document.location.replace("주소") 


* css 가져오기 : <link rel="stylesheet" href="주소"/> css 스크립트 이미지는 굳이 컨트롤러를 거칠 필요없이 static에서 불러오면 됨
* script 가져오기 : <sciprt src="주소"> </script>
* 이미지 보이기 : <img src="주소"/>
* 배경이미지 : background-image :url("주소")








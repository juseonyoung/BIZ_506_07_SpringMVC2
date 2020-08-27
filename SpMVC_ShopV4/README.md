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

***
***
# Spring Project 빛나리 쇼핑몰 V3

## 상품관리 거래처관리 이후에 회원가입, 로그인 구현 

* session
* Hyper text(Anchor Tag로 구성된 text를 웹 브라우저에서 클릭했을 때 해당하는 단어의 설명하는 새로운 문서가 열리는 구조로 된 문서)
* HTML(Hyper Text Markup language) 
	- 하이퍼 텍스트 기능을 구현하는 데 사용되는 마크업 언어
	
* HTTP(Hyper Text Transfer Protocol) 
	- 하이퍼 텍스트로 구성된 문서를 웹 브라우저에서 보여주고 사용자가 마우스로 Anchor tag 단어를 클릭하면 그에 따른
	  다른 문서를 연속해서 보여주는 용도로 만들기에 최적화된 인터넷 프로토콜
	
* HTTP 특징은 단방향이고 비 연결지향이다. 
	1. 웹 클라이언트에서 서버에 request를 보냈을 때만 서버에서 response 할 수 있다.
	2. 한번 요청-응답 과정에 이루어지면 그 연결은 끊어진다. 
	
* 서버의 어떤 연산을 요청할 때 요청하는 정보가 누구나 봐서는 안되는 정보라고 가정!
 서버에 연산을 요청할 때, "나는 그 정보를 봐도 되는 권한을 가지고 있다. 라고 알려줘야 한다.
 이러한 절차를 보통 로그인(Id, Password)라고 한다.
 서버는 클라이언트 req에서 사용자의 로그인 정보가 포함되어 있으면 그 정보를 확인하여 정상적이 ㄴ로그인 정보인지를 확인한다.
 이것을 "인증"이라고 한다. 
- 확인이 된 경우-> 요청한 정보를 client에서 response한다.
- HTTP는 즉시 클라이언트로 부터 받은 모든 정보(ID, Password 포함)를 삭제 해버린다. 
- 이후에 또다시 client 같은 정보를 요청을 할 필요가 있을 때에는 다시 한번 ID, password 를 req에 같이 실어서 보내야 한다. 
- 서버로부터 request해야 할 정보가 여러 페이지에 있을 경우 매 페이지를 요청할때마다 ID와 password를 보내고 
인증 후 response 하는 절차를 반복해야 한다.

- 이러한 불편을 해결하기 위해서 HTTPSession 이라고 하는 보조 프로토콜을 만들어 두었다. 

## HTTP Session(연결통로가 만들어졌다.)
1. client에서 로그인을 시도, ID와 password를 먼저 request한다. 
2. (인증절차)서버에서는 사용자가 보낸 ID와 password를 확인하여 정상적인 정보인지 검사한다. 
3. 서버는 정상적인 사용자 임이 확인되면 서버 메모리 어딘가에 HttpSession정보를 저장하기 위해 공간을 마련한다.
4. 이 공간에는 HttpSession규격에 따라 서버가 데이터를 보관한다.
5. 이 공간에는 session ID라고 하는 식별자(PK)을 만들어둔다.
6. server가 클라이언트 response 할 때 res 정보 생성된 세선ID를 같이 부착하여 보낸다.
7. 웹 브라우저는 서버로부터 전달된 reponse에 HttpSession 규격에 해당하는 sessionID가 있으면 브라우저의 임시 저장소에 보관한다. 
8. 이후에 client가 서버에 request하면 브라우저는 이 sessionID를 request에 같이 부착하여 서버로 보낸다. 
9. 서버에서 request를 수신했을 때 httpSession 규격에 맞는 세션아이디가 있으면, 서버가 정상적으로 발행(response에 부착하여 보낸)
	한 sessionID이고 & 유효기간이 정상적 이라면 이 요청은 "인가"된 클라이언트로부터 전달된 것임을 확인하고 이후 response를 수행해준다 

## 빛나리 쇼핑몰 V4

#### V3의 회원가입 문제점
* 기본 CRUD 는 모두 구현이 가능
* 회원정보 중에서 민감한 정보(ex. 비밀번호, 등)가 평문(plane text)으로 저장되어서 누군가 회원정보 DB를 select하게되면
 회원아이디와 비밀번호가 노출되어 보안상 매우 위험한 상황이 될 수 있다.
 
#### V3의 로그인 문제점
* 로그인이 필요한 기능을 사용자가 선택하면 로그인된 정보가 있는지 확인하고 없으면 로그인을 수행하도록 redirection된다

*각각의 컨트롤러/method에서 사용자 로그인 정보를 검사를 수행하고 있다.
만약 로그인이 필요한 기능이 많아진다면 상당히 불편한 상황이 발생한다.
로그인을 검사하고, 인증하는 절차에 해당하는 코드가 중복 작성되어야 한다. 

#### V4에서 회원가입, 로그인의 개선사항
1. 회원정보 중 민감한 정보를 암호화하여 DB 테이블에 저장
	-Bcrypt 암호화
	
2. 로그인 검사와 관련코드중 중복되는 코드를 모아서 처리하자.
	-intercepter 추가
	

	
	
	
	





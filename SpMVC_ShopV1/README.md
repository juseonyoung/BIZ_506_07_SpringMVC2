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
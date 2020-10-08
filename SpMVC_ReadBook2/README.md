# Read Book, 나의 독서록
* 내가 보유한 도서 등록
* 보유한 도서 읽기 일지 정리 : 독서록 정리 

## 도서 등록
* 도서명을 입력하여 네이버 도서 API 로부터 도서정보 가져오기 수행
* https://openapi.naver.com/v1/search/book.json?query=자바 (라는 형식으로 검색)
* 가져온 정보를 나의 DB에 저장!

## 독서록 정리
* 내가 보유한 도서정보 검색하여 해당 도서 읽기 일지를 등록하기 
* 등록할 때는 읽기 시작한 날, 읽기 종료한 날, 중간중간 진행상황 정리하기

## 로그인 기능
* Spring Security를 사용하여 사용자 정보 암호화 등을 수행

## 도서입력에서 네이버 검색정보 가져오기
* 도서명을 입력하고 네이버 검색버튼을 클릭하면 검색정보를 새로운 팝업창에 보여주고, 도서를 선택하면
 자동으로 입력박스에 채워지도록 구현
* 최근의 거의 대부분의 브라우저들이 팝업 차단기능을 구현해 놓고 사용자는 팝업기능을 활성화하여
 팝업으로 검색결과를 보여주는 데 문제가 발생을 한다.
* 요즘 UI는 팝업 대신에 MODAL 창 기법을 사용한다. 
* 모달- 팝업창 떴을 때 alert창 말고 다른 버튼 누르지 못하게 하는 것

* 도서명을 입력하고 네이버 검색 버튼을 클릭하면
  도서명을 ajax를 사용하여 검색하고 결과를 Modal 창에 띄운다. 
 
## write 입력폼 구현
*sessionattributes(), @modelattribute(), spring form taglib을 연동한 write(입력폼)구현
컨트롤러 클래스에 @sessionattributes("bookVO")를 설정하고 각 method에 매개변수로 
@modelattribute("bookVO") BookVO bookVO를 선언하고 
컨트롤러 클래스의 멤버영역에 @modelattribute("bookVO") public BookVO new BookVO() method 선언하고
spring form taglib를 이용한 write form <form:form modelattribute="bookVO">를 선언하여
프로젝트를 구현하면 

id, seq 등 실제 사용자에게 입력받거나 보여줄 필요가 없는 VO의 변수들을
<input type="hidden">으로 설정한 후 컨트롤러로 전송하던 HTML5 표준방식을 사용하지 않아도
VO에 설정된 변수들을 컨트롤러와 JSP가 서로 공유하여 사용할 수 있다. session쓰는 가장 큰 목적!
@sessionAttributes()에 담긴 VO 객체는 서버의 메모리에 보관되며
HTTP 프로토콜의 비연결지향(상태가 없는 통신) 상태에서도 데이터를 자유롭게 공유하여 구현할 수 있는 장점이 있다.

그럼에도 경우에 따라 입력 폼을 사용자에게 보여주엇을 때 마지막에 입력했던 데이터들이 form에 나타나서
불편한 경우가 있다.
이러한 현상을 방지하기 위해서 form에 입력되었던 데이터 사용이 끝나면(insert, update 완료 후)
sessionstatus.setComplete() method를 호출하여 데이터를 clear 시켜주어야 한다.  


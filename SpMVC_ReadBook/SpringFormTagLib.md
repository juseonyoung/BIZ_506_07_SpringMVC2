# spring form taglib
* spring project에서 사용할 수 있는 JSP 확장 taglib이다.
* form taglib를 사용하면 controller의 model 과 연동하여 input 관련 form을 비교적 쉽게 구현할 수 있도록 한다


## form tablib 프로젝트 작성 순서
1. form에서 input box를 구현하고 html5 태그라이브러리로 설정
	http://www.springframework.org/tags/form >> 이걸로 <%@ tab lib = ""@>
	
2. input box 의 name 값과 같은 이름의 필드변수를 갖는 vo 클래스를 작성한다

3. form 의 form tag를 form:form 으로, input 태그를 form:input으로 변경
4. input 태그의 id 속성을 모두 제거하고 name 속성을 path 속성으로 변경한다, 그러면 자동으로 name과 id값이 path에 지정한 값으로 바뀜
	
5. input 태그는 반드시 self closing한다 <form:input path="" /> 

6. form: form tag method는 제거해도 된다. 기본값이 method=POST로 설정되기 때문
7. action 부분을 필요에 따라 설정
8. modelAttribute 에 controller에서 model에 실어서 보내는 vo이름을 작성해야 한다
## 첨부파일이 있는 게시판
* 2개의 첨부파일까지 있는 게시판 작성

### INSERT 수행할 때
* 첨부된 파일을 파일이름을 변경하여 업로드를 수행하고 
* 변경된 파일이름을 VO에 저장한 후 테이블에 insert 수행하는 절차가 필요하다

### UPDATE 수행할 때
* 만약 새로운 파일이 업로드 되면 
* 기존 파일을 삭제하고, 업로드를 수행
* 만약 새로운 파일이 선택되지 않으면 기존 파일은 그대로 유지한 상태로 업데이트만 수행한다

### DELETE 수행할 때
* 먼저 첨부파일을 모두 삭제한 후 테이블의 데이터를 삭제

### naver smtp 설정값들
	<property name="javaMailProperties" >
			<props>
				<prop key="mail.transport.protocol">smtp</prop>
				<prop key="mail.smtp.auth">true</prop><!-- 이걸 true로 해야 로그인을 한 다음 절차가 이루어짐 -->
				<prop key="mail.smtp.starttls.enable"></prop> <!-- tls라는 암호화옵션 -->
				<prop key="mail.smtp.ssl.enable">true</prop>
				<prop key="mail.smtp.ssl.trust">smtp.naver.com</prop>
				<prop key="mail.debug">true</prop>
				
			</props>
		
		</property>
		
### gmail smtp 설정값들

	<property name="host" value="smtp.gmail.com"/>
		<property name="port" value="465"/>
		<property name="username" value="tmvjswl0123"/>
		<property name="password" value=""/>
		<property name="javaMailProperties" >
			<props>
				<prop key="mail.transport.protocol">smtp</prop>
				<prop key="mail.smtp.auth">true</prop><!-- 이걸 true로 해야 로그인을 한 다음 절차가 이루어짐 -->
				<prop key="mail.smtp.starttls.enable"></prop> <!-- tls라는 암호화옵션 -->
				<prop key="mail.smtp.ssl.enable">true</prop>
				<prop key="mail.smtp.ssl.trust">smtp.gmail.com</prop>
				<prop key="mail.debug">true</prop>
				
			</props>
		
		</property>
	
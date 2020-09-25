package com.biz.book.exec;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/*
 * jayspt 패키지의 StandardPBEStringEncryptor 클래스를 사용하여 
 * DB 접속용 username 과 password를 암호화 하여
 * 문자열을 추출
 * 
 */
public class MakeDBSecurity {

	public static void main(String[] args) {
		
		String propsFile ="./src/main/webapp/WEB-INF/spring/db.connect.properties";
		
		StandardPBEStringEncryptor pbe = new StandardPBEStringEncryptor();
		Scanner scan = new Scanner(System.in);
		
		Map<String, String> envList = System.getenv();
		String saltPassword = envList.get("BIZ.NET");
		
		if(saltPassword ==null) {
			System.out.println("BiZ.NET 환경변수 설정값이 없습니다. 확인하세요");
			return;
		}
		
		System.out.printf("BIZ.NET 환경변수 : %s\n", saltPassword);
		
		System.out.println("DB UserName >>");
		String UserName = scan.nextLine();
		
		System.out.print("DB Password >>");
		String password = scan.nextLine();
		
		// 암호화를 위해서 PBE.. 객체 값 세팅
		pbe.setAlgorithm("PBEWithMD5AndDES");
		
		// salt(setpassword): 암호화 할때 사용할 key 문자열
		// salt를 일반 문자열로 사용하면 소스코드에 문자열이 노출되어 각종 값들을 암호화 하는 용도로 사용하는데 문제가 된다
		// 문자열을 바로 사용하지 않고 시스템(컴퓨터 운영체제)에 환경변수를 저장해 두고
		// 환경변수를 가져다 salt 비밀번호로 사용을 한다. 
		
		//BIZ.COM 이라는 문자열이 노출되어도 네트워크를 통해서 salt암호를 추출하기가 매우 어려워진다. 
		// 따라서 암호화 하는 효과를 좀 더 배가 시킬 수 있다. 
		
		pbe.setPassword(saltPassword);
		// Password cannot be set empty 뜨면 환경변수가 지워졌다는 뜻- run configuration 다시
		
		String encUserName = pbe.encrypt(UserName);
		String encPassword = pbe.encrypt(password);
		
		String saveUserName = String.format("oracle.username=ENC(%s)", encUserName);
		String savePassword = String.format("oracle.password=ENC(%s)", encPassword);
		
		try {
			PrintWriter out = new PrintWriter(propsFile);
			out.println(saveUserName);
			out.println(savePassword);
			out.flush();
			out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //파일저장 클래스
		scan.close();
		System.out.println("DB 연결 속성파일 생성 완료!");
	}
}












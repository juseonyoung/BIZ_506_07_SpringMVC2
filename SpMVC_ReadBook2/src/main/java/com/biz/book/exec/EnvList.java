package com.biz.book.exec;

import java.util.Map;

public class EnvList {

	public static void main(String[] args) {
		
		
		Map<String, String> envList;
		
		// 컴퓨터 시스템의 환경변수들을 map 객체로 추출하는 method
		// 환경변수 바꾸면 다시 켜야함 내pc-속성-고급-환경변수-아래에서 새로만들기 
		envList = System.getenv();
		
		System.out.println("BIZ.COM:" + envList.get("BIZ.COM")); //BIZ.COM이라는 환경변수가 있으면 그 값을 추출해라
		System.out.println("BIZ.NET:" + envList.get("BIZ.NET")); //Run configuration - 환경 -Add-
	}
}

package com.biz.shop.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biz.shop.service.ProductService;

/*
 * 	restcontroller를 쓰게 되면 표준 콘트롤러는 return 문자열 을 가지고 view 파일을 읽고
 * 랜더링을 수행한 후 web client 에 HTML 코드로 response를 수행한다.
 * 그에 비해 restcontroller는 view가 필요없이 데이터를 직접 클라이언트에 전송할 때 사용하는 콘트롤러이다
 *  
 * 표준 콘트롤러에서는 특정한 메서드를 지정하여 view가 아닌 데이터를 직접web client 로 전송 할 때
 * 메서드에 @responserbody 를 설정해 준다. 
 * restcontroller는 모든 method에 @Responsebody가 설정된 것과 같은 효과를 낸다.
 * 
 *  requestMapping을 api로 시작한 이유
 *  API(application programmer interface)는 서버와 서버 간 , 서비스와 서비스 간 데이터를 주고받는
 *  방식을 일컫는 용어이다. 
 *  
 * 
 */
@RestController
@RequestMapping(value="/api/product")
public class ProductRestController {

	@Autowired
	@Qualifier("proServiceV1")
	ProductService proService;
	
	
	
	@RequestMapping(value="/get_pcode", method=RequestMethod.GET)
	public String getPCode() {

		/*
		 * 요구사항!
		 * ProductService의 getpcode() 호출하여
		 * 새로운 상품코드를 생성하여 받고 싶다. 
		 * 
		 * TDD(Test Driven Developer)
		 * 요구사항을 먼저 만들고 실제 구현되는 코드를 나중에 만드는 방식
		 * 
		 */
		String strPCode = proService.getPCode();
		
		return strPCode;
	}
}











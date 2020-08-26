package com.biz.shop.service;

import java.util.List;

import com.biz.shop.model.ProductVO;


/*
 * ProductService 인터페이스는 GenericService 인터페이스를 상속(extends) 함으로써
 * CRUD 기본 메서드를 별도로 선언하지 않아도 된다. 
 * 필요한 추가 메서드가 있으면 별도로 선언을 해주고 이 인터페이스를 implements 한 클래스는
 * 인터페이스의 영향을 받아 메서드를 구현하게 된다. 
 * 
 */
public interface ProductService extends 
		GenericService<ProductVO, String>{ // 아무런 서비스가 없어도 제네릭 이용하여 서비스 구현 
	
	public List<ProductVO> findByTitle(String title);
	// 이렇게 제네릭에 없는 메서드 필요하면 추가하여 사용 가능!

	// 1. Controller에서 getPCode() method를 "사용하는" 코드를 먼저 작성 후 
	// 2. 실제 구현되는 코드를 만들기 위해서 interface에 method를 자동 생성하기
	
	
	public String getPCode();


	
	// 서비스(인터페이스) 다오 클래스 만드는 반복작업
}

package com.biz.shop.service;

import java.util.List;

import com.biz.shop.model.ProductVO;

/**
 * 
 * GenericService interface 파라미터를 VO와 PK로 받도록 설계되었다를 나타냄
 * @param <VO>
 * @param <PK>
 * 
 * DB와 관련된 프로젝트 수행할 때 
 * Service 클래스 만들기에 앞서 서비스 인터페이스를 만들도록 권하고 있다.
 * 
 * 다수의 테이블이 존재하는 프로젝트일 경우에는 비슷한(같은) 이름의 method를
 * 중복하여 선언해야 하는 경우가 많다. 이런 경우에 서비스인터페이스를 만들기 위한 상위 인터페이스를 선언하고
 * 인터페이스에 Generic<VO, PK>을 선언해 둔다.
 * 이 인터페이스를 상속받아서 실제사용할 인터페이스를 선언해주면
 * 기본 method는 별도로 작성(코딩)하지 않아도 클래스를 생성할 때 자동으로 method를 선언할 수 있는 기능을 구현할 수 있다.  
 *  
 */
// 두개의 클래스 인터페이스를 합하는 작업
public interface GenericService<VO,PK> { //제레릭 안에 임의로 붙이는 변수 

	public List<VO> selectAll(); //VO라는 리스트가 포함된 클래스
	
	public VO findById(PK id); // ID = PK라는 개념으로 생성하는 mnethod 
	
	public int insert(VO vo); //proVO 이용해 추가
	public int update(VO vo);
	public int delete(PK id);
}

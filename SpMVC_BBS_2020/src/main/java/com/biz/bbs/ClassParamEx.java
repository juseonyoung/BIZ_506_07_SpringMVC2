package com.biz.bbs;

import com.biz.bbs.model.BBsVO;

public class ClassParamEx {

	public static void main(String[] args) {

		BBsVO bbsVO = new BBsVO();
		long b_seq =99;
		System.out.println(b_seq);
		b_seq(b_seq); // 아래 메서드에서 long의 타입은 primitive형이기 때문에 b_seq값이 메서드 밖으로 절대 나오지 못함! 값은 여전히 99
		System.out.println(b_seq);
		
		bbsVO.setB_seq(b_seq);
		System.out.println(bbsVO.getB_seq()); //99
		b_seq(bbsVO);
		System.out.println(bbsVO.getB_seq());
	}

	private static void b_seq(BBsVO vo) { //BBsVO의 타입이 VO클래스형(참조형) 일반 클래스와 비교!
		// 안에서 새로 선언해도 메서드 밖으로 못 나옴
		//vo = new BBsVO(); //매개변수로 받은 vo를 다시 생성함
		
		vo.setB_seq(1000);
		System.out.println(vo.getB_seq());
	}
	
	private static void b_seq(long b_seq) {

		b_seq = 999;
	}

	
}









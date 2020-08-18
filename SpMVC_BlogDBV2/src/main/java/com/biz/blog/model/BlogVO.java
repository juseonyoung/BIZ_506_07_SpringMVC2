package com.biz.blog.model;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlogVO {
	
	/*
	 * 클래스 만들 때 변수값 초기화 하지 않아도 이 클래스로 객체를 생성하면
	 * 숫자형변수는 0으로 문자열형 변수는 ""으로 자동 초기화된다. 
	 * 
	 */
	private long bl_seq;
	private String bl_user;
	private String bl_title;
	private String bl_contents;

	private String bl_date;
	private String bl_time;
	
}

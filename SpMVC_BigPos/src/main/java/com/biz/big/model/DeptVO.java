package com.biz.big.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DeptVO {

	private String d_code; //거래처코드
	private String d_name; //거래처명
	private String d_sid; //사업자번호
	private String d_ceo; //대표자명
	private String d_tel; //대표전화
	private String d_addr; //주소
	private String d_product; //주요품목
}

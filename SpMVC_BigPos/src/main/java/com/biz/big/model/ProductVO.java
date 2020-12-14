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
public class ProductVO {

	private String p_code; //상품코드
	private String p_name; //상품명
	private String p_item; //품목
	private String p_menuf; //제조원
	private String p_dcode; //주매입처
	private String p_vat; //과세구분
	private int p_iprice; //매입단가
	private int p_oprice; //판매단가
}

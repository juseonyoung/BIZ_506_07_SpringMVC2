package com.biz.big.model;

import lombok.Builder;

import lombok.AllArgsConstructor;
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
public class PosVO {

	private long seq; //숫자
	private String io_date;
	private String io_time;
	private String io_dcode; //거래처코드
	private String io_inout; //거래구분
	private String io_pcode; //상품코드
	private String io_vat; //과세여부
	private int io_qty; //수량
	private int io_price;  //단가
	private int io_amt; //금액
	private int io_tax; //세액
	private int io_total; //합계
}




package com.biz.shop.model;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class ProductVO {

	
	private String p_code;//	char(6)
	private String p_name;//	nvarchar2(30)
	private String p_dcode;//	char(4)
	private String p_std;//	nvarchar2(20)
	private int p_iprice;//	number
	private int p_oprice;//	매입 매출을 썼다면 int형으로 바꾸도록 할 것.. 하지만 공백이면 숫자로 전환할 값이 없어 400 오류가 뜬다
	// null로 인식하기 때문에 0이라도 써야 함 
	private String p_image;//	nvarchar2(125)

}

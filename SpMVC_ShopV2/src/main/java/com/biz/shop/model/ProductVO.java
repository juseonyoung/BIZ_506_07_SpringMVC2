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
	
	
	//DB와 연동할 때 byte를 CHAR(1)  tinyint 형으로 선언된 칼럼과 연동하기 위해 사용하는 type이다.
	// 이 type 0~255 , -128 부터 127까지의 값만을 저장하는 데이터이다. 
	// 메모리 공간 크기가 1byte(8bit) 
	// int 형은 java에서 4byte 크기를 사용한다. 
	// String 형은 wrapper 클래스 이다 보니 실제적으로 상당히 큰 메모리 공간을 차지한다. 
	// 단순히flag 와 같은 데이터를 취급할 때는 char, byte형으로 사용하면 메모리를 절약할 수 있다. 
	
	
	
	// DB p_not_use 칼럼의 값이 NULL(is null)이면 정상적인 데이터이고 
	// null이 아니면 (is not null) 이면 삭제된 데이터로 취급하기로 하였다.
	// update를 수행할 때 해당 VO의 칼럼에 값이 null이면 jdbcType.VARCHAR, config에 수행한 설정때문에
	// DB에 업데이트할 때 null이 아닌 다른 값이 저장되어 버린다. 
	// Dao.update() 수행하기 전에 VO에 해당 칼럼의 값을 강제로 NULL 해줄 필요가 있다.
	// 하지만 기본형인 변수는 null값을 저장하여 DB로 보낼 수 없다. 
	// 때문에 이 칼럼의 타입을 byte 형이 아닌 Byte wrapper 클래스형으로 설정한다. 
	private Byte p_not_use;

}

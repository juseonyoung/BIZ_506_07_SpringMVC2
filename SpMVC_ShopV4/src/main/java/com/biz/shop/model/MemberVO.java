package com.biz.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MemberVO {

	private String m_userid;//	varchar2(20)
	private String m_password;//	varchar2(125)
	private String m_name;//	nvarchar2(30)
	private String m_tel;//	varchar(20)
	private String m_address;//	nvarchar2(255)
	private String m_roll;//	varchar(20)
}

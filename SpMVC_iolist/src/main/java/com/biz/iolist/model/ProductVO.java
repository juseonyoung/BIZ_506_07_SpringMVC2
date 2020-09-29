package com.biz.iolist.model;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
	
	private int io_seq; //number, pk
	private String io_date; //varchar2
	private String io_time; //varchar
	private String io_input; //char
	private String io_pname; //nvarchar, not null
	private int io_price; //number
	private int io_quan; //bumber
	private int io_total; //number
	
}

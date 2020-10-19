package com.biz.bbs.model;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class BBsVO {

	private long b_seq;
	private String b_date;
	private String b_time;
	private String b_writer;
	private String b_subject;
	private String b_content;
	private int b_count;
	private String b_file;

}














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
	
	private long bl_seq;
	private String bl_user;
	private String bl_title;
	private String bl_contents;

	private String bl_date;
	private String bl_time;
	
}

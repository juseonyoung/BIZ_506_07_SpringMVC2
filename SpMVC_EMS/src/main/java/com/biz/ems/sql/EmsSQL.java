package com.biz.ems.sql;

import org.apache.ibatis.jdbc.SQL;

public class EmsSQL {

	public String ems_insert() {
		
		SQL sql = new SQL();
		sql.INSERT_INTO("tbl_ems");
		
		sql.INTO_COLUMNS("id").INTO_VALUES("#{id}");
		sql.INTO_COLUMNS("from_email").INTO_VALUES("#{from_email}");
		sql.INTO_COLUMNS("to_email").INTO_VALUES("#{to_email}");
		sql.INTO_COLUMNS("s_date").INTO_VALUES("#{s_date}");
		sql.INTO_COLUMNS("s_time").INTO_VALUES("#{s_time}");
		sql.INTO_COLUMNS("s_subject").INTO_VALUES("#{s_subject}");
		sql.INTO_COLUMNS("s_content").INTO_VALUES("#{s_content}");
		sql.INTO_COLUMNS("s_file1").INTO_VALUES("#{s_file1}");
		sql.INTO_COLUMNS("s_file2").INTO_VALUES("#{s_file2}");
		
		return sql.toString();
	}
	
	
	
	public String ems_update() {
		
		SQL sql = new SQL();
		sql.UPDATE("tbl_ems");
		sql.SET("from_email = #{from_email}");
		sql.SET("to_email = #{to_email}");
		sql.SET("s_date = #{s_date}");
		sql.SET("s_time = #{s_time}");
		sql.SET("s_subject = #{s_subject}");
		sql.SET("s_content = #{s_content}");
		sql.WHERE("id=#{id}");
		
		return sql.toString();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

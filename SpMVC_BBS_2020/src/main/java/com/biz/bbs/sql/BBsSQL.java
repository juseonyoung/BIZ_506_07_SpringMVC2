package com.biz.bbs.sql;

import org.apache.ibatis.jdbc.SQL;

/*
 * xml을 사용하지 않는 mybatis SQL query bulder 사용하기
 * mybatis 3.4 이상에서 새로 작성된 sql query builder를 사용하여 xml없이 
 * 마이바티스 db mapper를 구현
 * dynamic SQL 
 * 
 */
public class BBsSQL {

	public String bbs_insert() {
		
		SQL sql = new SQL(); //페이지네이션 구현할 때 
		
		
		sql.INSERT_INTO("tbl_bbs");
		sql.INTO_COLUMNS("b_seq").INTO_VALUES("seq_bbs.NEXTVAL");
		sql.INTO_COLUMNS("b_date").INTO_VALUES("#{b_date}");
		sql.INTO_COLUMNS("b_time").INTO_VALUES("#{b_time}");
		sql.INTO_COLUMNS("b_writer").INTO_VALUES("#{b_writer}");
		sql.INTO_COLUMNS("b_subject").INTO_VALUES("#{b_subject}");
		sql.INTO_COLUMNS("b_content").INTO_VALUES("#{b_content}");
		sql.INTO_COLUMNS("b_file").INTO_VALUES("#{b_file}");
		sql.INTO_COLUMNS("b_count").INTO_VALUES("#{b_count}");
		
		return sql.toString();
	}
	
	// sql 클래스를 사용하여 xml 대신 java code 방식으로 sql 작성
	public String bbs_update() {
		SQL sql = new SQL();
		
		sql.UPDATE("tbl_bbs");
		sql.SET("b_date=#{b_date}");
		sql.SET("b_time=#{b_time}");
		sql.SET("b_writer=#{b_writer}");
		sql.SET("b_subject=#{b_subject}");
		sql.SET("b_content=#{b_content}");
		sql.WHERE("b_seq = #{b_seq}");
		
		return sql.toString();
	}
}
















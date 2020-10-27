package com.biz.ems.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.biz.ems.model.ImageVO;
import com.biz.ems.sql.ImageSQL;

public interface ImageDao {

	@Select("SELECT * FROM tbl_emsimages WHERE i_b_seq = #{i_b_seq}")
	public List<ImageVO> fineBySeq(long i_b_seq);
	
	@InsertProvider(type=ImageSQL.class, method="insert")
	public int insert(@Param("vo") ImageVO imageVO, @Param("b_seq") long b_seq);
	
	public int insert_list(List<ImageVO> images, long b_seq);
}

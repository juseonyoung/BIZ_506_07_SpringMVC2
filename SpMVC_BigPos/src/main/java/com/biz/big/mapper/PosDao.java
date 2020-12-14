package com.biz.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.biz.big.model.PosVO;

public interface PosDao {

	@Select("SELECT * FROM tbl_poslist")
	public List<PosVO> selectAll();
	
	public int insert(PosVO posVO);
	
	@Select("SELECT * FROM tbl_poslist WHERE seq = #{seq}")
	public PosVO findById(Long seq);
	
	@Delete("DELETE FROM tbl_poslist WHERE seq= #{seq}")
	public int delete(Long seq);
	
	public int update(PosVO posVO);
}

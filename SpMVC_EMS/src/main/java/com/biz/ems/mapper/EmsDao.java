package com.biz.ems.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.ems.model.EmsVO;
import com.biz.ems.sql.EmsSQL;

public interface EmsDao {

	@Select("SELECT * FROM tbl_ems")
	public List<EmsVO> selectAll();
	
	@Select("SELECT * FROM tbl_ems WHERE id = #{seq}")
	public EmsVO findBySeq(long seq);
	
	
	@InsertProvider(type=EmsSQL.class, method="ems_insert")
	@SelectKey(keyProperty = "id", statement = "SELECT SEQ_EMS.NEXTVAL FROM DUAL",
	resultType = Long.class, before = true)
	public int insert(EmsVO emsVO);
	
	
	@UpdateProvider(type=EmsSQL.class, method="ems_update")
	public int update(EmsVO emsVO);
	
	
	@Delete("DELETE FROM tbl_ems WHERE id =#{seq}")
	public int delete(long seq);
	

	
}

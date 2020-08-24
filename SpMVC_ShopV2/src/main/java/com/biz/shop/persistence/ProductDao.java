package com.biz.shop.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.shop.model.ProductVO;

public interface ProductDao extends GenericDao<ProductVO, String> {

	
	@Select(" select * from tbl_product")
	@Override
	public List<ProductVO> selectAll();

	public List<ProductVO> findByTitle(String title);

	/*
	 * 마이바티스 3.4.x 이상에서 사용하는 새로운
	 * 
	 * */

	@Select("SELECT MAX(p_code) FROM tbl_product")
	public String maxPCode();

	@Select(" select * from tbl_product WHERE p_code =RPAD(#{id},6, ' ') ")
	@Override
	public ProductVO findById(String id);
}

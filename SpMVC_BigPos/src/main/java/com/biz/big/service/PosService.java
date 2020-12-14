package com.biz.big.service;

import java.util.List;

import com.biz.big.model.PosVO;

public interface PosService {

	public List<PosVO> selectAll();
	public PosVO findById(Long seq);
	public int insert(PosVO posVO);
	public int update(PosVO posVO);
	public int delete(Long seq);
	
}

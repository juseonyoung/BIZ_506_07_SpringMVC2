package com.biz.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.bbs.mapper.BBsDao;
import com.biz.bbs.model.BBsVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("bbsServiceV1")
public class BBsServiceImplV1 implements BBsService{

	private final BBsDao bbsDao;

	@Override
	public List<BBsVO> selectAll() {
		// TODO Auto-generated method stub
		
		return bbsDao.selectAll();
	}

	@Override
	public void insert(BBsVO bbsVO) {
		log.debug(bbsVO.toString());
		bbsDao.insert(bbsVO);
		
	}

	@Override
	public BBsVO findBySeq(long long_seq) {
	
		return bbsDao.findBySeq(long_seq);
	}
	
}

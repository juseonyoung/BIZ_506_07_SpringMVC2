package com.biz.iolist.mapper;

import java.util.List;

public interface GenericDao<VO, PK> {

	public List<VO> selectAll(); // VO라는 리스트가 포함된 클래스

	public VO findById(PK id); // ID = PK라는 개념으로 생성하는 mnethod

	public int insert(VO vo); // proVO 이용해 추가

	public int update(VO vo);

	public int delete(PK id);

}

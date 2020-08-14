package com.biz.blog.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.blog.dao.BlogDao;
import com.biz.blog.model.BlogVO;

/*
 *  No qualifying bean of type 'com.biz.blog.service.BlogService'
 *  spring 프로젝트에서 서버 시작했을 때 매우 자주 만나는 exception
 *  controller, service 등의 클래스에 annotation을 부착하지 않았을 때 
 */

@Service
public class BlogServiceImplV1 implements BlogService{
	
	
	//객체 주입
	// autowired를 빼먹으면 바로 nullpointerexception
	@Autowired
	// mybatis-context에서 설정한 SqlSessionTemplate을 가져와서 사용할 수 있도록 선언
	
	private SqlSession sqlSession;
	

	@Override
	public List<BlogVO> selectAll() {

		// blogDao와 Sqlsession을 연동하여 마이바티스 연결 구성
		// sqlSession 에게 BlogDao 인터페이스와 Blog-mapper.xml 파일을 참조하여 
		// BlogDao 인터페이스를 구현한 클래스를 만들고, 객체로 생성하여 사용할 수 있도록 해 달라
		
		BlogDao blogDao =sqlSession.getMapper(BlogDao.class);
		
		List<BlogVO> blogList = blogDao.selectAll();
		return blogList;
	}
	

	@Override
	public BlogVO findBySeq(long seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogVO> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BlogVO blogVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BlogVO blogVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long seq) {
		// TODO Auto-generated method stub
		return 0;
	}

}

package com.biz.blog.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.biz.blog.dao.BlogDao;
import com.biz.blog.model.BlogVO;

/*
 *  No qualifying bean of type 'com.biz.blog.service.BlogService'
 *  spring 프로젝트에서 서버 시작했을 때 매우 자주 만나는 exception
 *  controller, service 등의 클래스에 annotation을 부착하지 않았을 때 
 */

@Service(value="bServiceV1")
public class BlogServiceImplV1 implements BlogService {

	// 객체 주입
	// autowired를 빼먹으면 바로 nullpointerexception
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BlogVO> selectAll() { 

		// blogDao와 Sqlsession을 연동하여 마이바티스 연결 구성
		// sqlSession 에게 BlogDao 인터페이스와 Blog-mapper.xml 파일을 참조하여
		// BlogDao 인터페이스를 구현한 클래스를 만들고, 객체로 생성하여 사용할 수 있도록 해 달라

		BlogDao blogDao = sqlSession.getMapper(BlogDao.class);

		List<BlogVO> blogList = blogDao.selectAll();
		return blogList;
	}

	@Override
	public BlogVO findBySeq(long seq) {
		BlogDao blogDao = sqlSession.getMapper(BlogDao.class);
		BlogVO blogVO = blogDao.findBySeq(seq);
		return blogVO;
	}

	@Override
	public List<BlogVO> findByTitle(String title) {

		return null;
	}

	/*
	 * insert문 실행할 때 많이 발생하는 Exception java.sql.SQLException: 부적합한 열 유형: 1111
	 * Mybatis를 사용하여 insert를 수행할 때 not null이 아닌 칼럼의 값이 없으면 발생하는 Exception이다
	 * 
	 * 날짜와 시간칼럼의 값이 없어서 발생하는 Exception
	 * 
	 */
	@Override
	public int insert(BlogVO blogVO) {
		BlogDao blogDao = sqlSession.getMapper(BlogDao.class);

		// 작성한 날짜와 시각을 VO에 담기위해 insert 서비스에서 날짜 시각 생성
		Date date = new Date(System.currentTimeMillis());

		// Date형 데이터를 문자열형으로 변환하기 위해
		// 2020-08-18 형식의 문자열로 변환하기 위한 format 선언
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

		// 09:32:00 형식의 문자열로 변환하기 위한 포맷 선언
		SimpleDateFormat st = new SimpleDateFormat("hh:mm:dd");

		// 문자열형으로 날짜와 시각을 변환하여 VO에 담기
		blogVO.setBl_date(sd.format(date));
		blogVO.setBl_time(st.format(date));

		int ret = blogDao.insert(blogVO);
		return ret;
	}

	@Override
	public int update(BlogVO blogVO) {
		BlogDao blogDao = sqlSession.getMapper(BlogDao.class);
		return blogDao.update(blogVO);
	
	}

	@Override
	public int delete(long seq) {

		BlogDao blogDao = sqlSession.getMapper(BlogDao.class);
		return blogDao.delete(seq);

	}

}

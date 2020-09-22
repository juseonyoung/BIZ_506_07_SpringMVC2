package com.biz.book.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biz.book.config.NaverSecret;
import com.biz.book.model.Book_JSON_Parent;
import com.biz.book.model.BookVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("naverServiceV2")
public class NaverServiceImplV2 extends NaverServiceImplV1{
	
public String queryURL(String category, String bookName) {
		
		String queryURL =NaverSecret.NAVER_BOOK_JSON; //xml방식으로 요청하겟다 jaxb runtime 2.3.3이 필요
		String encodeText =null;
		try {
			// 한글 검색을 위해서 검색어를 UTF-8로 엔코딩 처리해주어야 한다. 
			encodeText = URLEncoder.encode(bookName.trim(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 // 여기까지 필수문자 url?query=자바
		queryURL += String.format("?query=%s",encodeText); 
		
		// 한번에 조회할 테이터 개수를 지정할 수 있다
		queryURL += "&display=+10"; //한번에 10개
		
		return queryURL;
	}



	// naverServiceV1의 3개의 method를 모두 상속받아서
	// 이곳에 아무런 코드가 없어도 작동한다. 
	
	// 3개의 method중에서 getNaverList(jsonString) 메서드만 업그레이드하겟다
	
	@Override
	public List<BookVO> getNaverList(String queryURL) {
		// TODO Auto-generated method stub
		
		// queryURL(요청주소포함된) 문자열을 URI 객체로 만들어 http 프로토콜에서 사용할 수 있도록 만드는 클래스
		// 기존에 URL 클래스가 있으나, 새로운 기능을 수행하기 위해서 별도로 uri라는 클래스를 만들어 놨으며 많은 기능을이 추가되어있다. 
		// RestTemplate를 사용하기 위해서는 쿼리문자열을 URL이 아닌 URI로 사용해야 한다.  
		
		URI restURI = null; // rest쓸때는 url 말고 uri 써라
		
		/*
		 * Springframework에서 외부 API를 사용하여 데이터를 가져올 때 기존에는 JSON(XML) 형식으로 가져오고,
		 * 여러가지 외부라이브러리를 사용하여 객체로 parsing하는 과정을 복잡하게 만들어야 했다. 
		 * 
		 * spring에서 외부 API를 사용하여 데이터를 가져올 일이 점점 많아지면서 RestTemplate라는 클래스를 새로
		 * 만들어 framework 프로젝트에서 사용할 수 있도록 만들어 두었다. 
		 * 
		 * HttpHeader, HttpEntity, ResponseEntity 객체만 잘 작성하면 외부 API에 요청하고
		 * 응답받은 데이터를 파싱하는 절차를 대신 수행해준다. 
		 * 
		 */
		RestTemplate restTemp = new RestTemplate();
		
		try {
			restURI = new URI(queryURL);
		} catch (Exception e) {
			// TODO: handle exception
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		headers.set("X-Naver-Client-Id",NaverSecret.NAVER_CLIENT_ID);
		headers.set("X-Naver-Client-Secret",NaverSecret.NAVER_CLIENT_SECRET);
		
		HttpEntity<String> entity = new HttpEntity<String>("parameter",headers); // 우리가 보낼 정보를 헤더에 저장
		
		// 헤더들을 모아 클래스 HttpHeaders로 만듦 서버로 보내기 위한 테이블
		
		ResponseEntity<Book_JSON_Parent> bookList = null; //데이터 수신하기 위한 몸체 
		// 네이버가 보낸 데이터를 BookList클래스 구조로 받겠다 라고 선언!!!!!!!! 가 중요
		
		
		bookList = restTemp.exchange(restURI, HttpMethod.GET,entity,Book_JSON_Parent.class);
		// 데이터 가져와라 restURI 주소로 요청했으니(get방식으로)
		// header가 포함된 entity를 주입 BookList.class를 너에게 알려주니 받아서 BookList 형태로 잘게 쪼개라
		// 쪼갠형태-> V1에 있음
		
		log.debug(bookList.toString());
		return bookList.getBody().items; // 헤더말고! 바디부분만 추출하여 items 데이터만 추출 실질적인 값만 담김
	}
	
	
}

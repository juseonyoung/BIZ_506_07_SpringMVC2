package com.biz.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biz.book.model.BookVO;
import com.biz.book.service.NaverService;

import lombok.extern.slf4j.Slf4j;

/*
 * view를 return response하지 않고 객체, 문자열 등을 직접 JSON 형태로 response 하는 controller
 * 메서드 위에 @responsebody 붙이는 것과 같다
 * 
 */

@Slf4j
@RestController
@RequestMapping("/api")
public class BookApiController {

	@Autowired
	@Qualifier(value="naverServiceV2")
	private NaverService<BookVO> nService;
	
	@RequestMapping(value="/isbn", method=RequestMethod.POST, produces= "application/json;charset=utf8") //json 형태로 요청
	public BookVO naverSearch(String search_text){
		
		String queryURL = nService.queryURL("BOOK", search_text);
		BookVO bookVO = nService.getNaverList(queryURL).get(0); //데이터값이 1개일지라도 리스트로 
		
		// getNaverList(query) method는 도서명, ISBN으로 조회를 하여
		// 도서 리스트를 returnㅎㅐ주는 메서드이다.
		// 도서명으로 검색하면 당연히 1개이상의 데이터가 return되어 lost형이 될 것
		// 만약 ISBN으로 검색하면 isbn은 중복값이 없으므로 1개만 return 될것이다
		// 하지만 이 메서드는 태생이 list를 return하도록 만들어졌기 때문에 
		// 1개의 데이터라도 List<BookVO>에담겨서 리턴된다
		// ISBN으로 조회를 했을 때는 다른 데이터는 있더라도 무시하고 첫번째(0번)데이터만 필요하므로 ... get(0)으로 첫번째 데이터 추출한다
		
		log.debug("도서정보 :"+bookVO.toString());
		return bookVO; //데이터를 json형태로 보냄
	}
	
}












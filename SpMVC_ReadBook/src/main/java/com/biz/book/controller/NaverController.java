package com.biz.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.book.model.BookVO;
import com.biz.book.service.NaverService;

@RequestMapping(value="/naver")
@Controller
public class NaverController {
	
	@Autowired
	@Qualifier("naverServiceV1")
	private NaverService<BookVO> nServiceV1;
	

	@Autowired
	@Qualifier("naverServiceV2_XML")
	private NaverService<BookVO> nServiceV2;
	

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String search(String search_text) {
		
		return "naver/naver-list";
		
	
		
	}
	
	/*
	 * @requestParam()
	 * controller의 매개변수를 재 정의하는 용도
	 * 만약 클라이언트에서 보내는 변수 이름과 컨트롤러에서 사용하는 변수 이름을 다르게 하고싶으면
	 * @RequestParam(name="변수") String 내 이름
	 * 
	 * 클라이언트에서 해당 변수값을 빼먹고 전송을 했을 때 오류를 최소화 하기 위해서 
	 * @RequestParam(name="내 이름",required=false, defaultValue="홍길동) String 내이름
	 * 클라이언트에서 내이름 변수에 값을 보내지 않으면 기본값인 홍길동 문자열을 내이름 변수에 할당한다. 
	 * 
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(@RequestParam(name="category", required=false, //클라이언트가 카테고리에 어떠한 값이 실려오지 않아도 수용해라
							//요구되는 것이 없음 그럴 때는 BOOk이라는 문자열을 카테고리 변수에 할당하라 
								defaultValue="BOOK") String category, 
			@RequestParam(name="search_text")String search_text, Model model) {
			// @RequestParam 붙이는 이유?? 
		
		
		String queryURL
			=nServiceV2.queryURL(category, search_text);
		
		String jsonString = nServiceV2.getNaverSearch(queryURL);
		
		// V1에서 사용되는 methodc
		//List<BookVO> bookList = naverService.getNaverList(jsonString);
		
		List<BookVO> bookList = nServiceV2.getNaverList(queryURL);
		
		//log.debug(bookList.toString());
		model.addAttribute("NAVERS", bookList);
		return "naver/naver-search-list";
	}
	
	@ResponseBody
	@RequestMapping(value ="/api", method=RequestMethod.POST,
									produces="application/xml;charset=utf8") //
	public String naver(String book_name) {
		String queryURL =nServiceV1.queryURL("BOOK", book_name);
		String retString = nServiceV1.getNaverSearch(queryURL);
		return retString;
		
		//List<BookVO> bookList = naverService.getNaverList(jsonString);
		//return bookList;
		
		
	}
	// 호출할때는?
	// .../query?num1=30&num2=40 이런식으로 호출
	@ResponseBody
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public String query(int num1, 
			@RequestParam(name="num2", required=false, defaultValue="500") int num2) {
		// num2에 값을 보내지 않아도 자동으로 500값을 넣어 보내서 num1+num2 이 나옴
		// name과 변수이름이 다르다면 requestparam 써야함 
		
		return (num1+num2)+""; //String으로 받았으니까!
		
	}
}









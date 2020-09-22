package com.biz.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.book.mapper.BookDao;
import com.biz.book.model.BookVO;

@Controller
@RequestMapping(value="/books")
public class BooksController {

	@Autowired
	private BookDao bookDao;
	
	// localhost:8080/book/books
	// localhost:8080/book/books/ 슬래스 넣을때나 넣지 않을때나 같은 값 나오도록
	
	//@ResponseBody //객체를 그대로 json형태로 내려보내라 
	@RequestMapping(value={"/",""} ,method=RequestMethod.GET) //문자열배열
	public String list(Model model){
		
		List<BookVO> bookList = bookDao.selectAll();
		model.addAttribute("BOOKS",bookList);
		return "books/list";
	}
	
	@RequestMapping(value="/input",method=RequestMethod.GET)
	public String input() {
		
		// 컨트롤러의 mapping method의 return type이 String일 때 
		// null 값을 return 하면 method 를 호출할 때 사용했던 mapping URL.jsp 형식의 return문이 자동으로
		// 생성된다. 
		return "books/write";
	}
}













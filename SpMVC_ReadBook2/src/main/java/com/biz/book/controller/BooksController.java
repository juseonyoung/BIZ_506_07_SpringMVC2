package com.biz.book.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.book.mapper.BookDao;
import com.biz.book.mapper.ReadBookDao;
import com.biz.book.model.BookVO;
import com.biz.book.model.ReadBookVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/*
 * @transactional annotaion 을 클래스 차원에서 설정하면
 * 현재 클래스의 모든 method에서 DB와 연동되는 부분이 자동으로 Transactional 이 작동된다
 * 
 */

@SessionAttributes("bookVO") //session은 서버와 클라이언트를 연결하는 통로 컨트롤러 시작과 동시에 bookVO라는 이름으로 서버에 저장공간을 만든다
// 이름을 bookVO라고 지정. method안중요 x 리턴타입을 BookVO로 하고 modelattribute라는 어노테이션 지정.
// 이 메서드가 VO를 만드는 command method / bookVO라는 변수에 값을 채워넣음

@RequiredArgsConstructor
@Transactional
@Slf4j
@Controller
@RequestMapping(value = "/books")
public class BooksController {

	
	private final BookDao bookDao;
	private final ReadBookDao rbookDao;
	
	@ModelAttribute("bookVO")
	public BookVO newBookVO() {
		
		LocalDate localDate = LocalDate.now();
		String todayString = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
		// 컨트롤러의 mapping method의 return type이 String일 때
		// null 값을 return 하면 method 를 호출할 때 사용했던 mapping URL.jsp 형식의 return문이 자동으로
		// 생성된다.
		BookVO bookVO = BookVO.builder().buydate(todayString).buyprice(10000).build();
			return bookVO;
	}

	// localhost:8080/book/books
	// localhost:8080/book/books/ 슬래스 넣을때나 넣지 않을때나 같은 값 나오도록

	// @ResponseBody //객체를 그대로 json형태로 내려보내라
	
	
	// Sqlsessiontemplate 대신 datasourcetransactionmanager 이것을 mybatis-context.xml 에 만들어주고
	// appservlet/tx-context.xml 을 만들고
	// <tx:annotaion-driven/> 을 설정하고 transaction이 필요한 method에 @Transactional annotaion 을 설정해주면
	// 자동으로 기본정책으로 transaction 이 수행된다. 
	
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET) // 문자열배열
	public String list(Model model) {

		List<BookVO> bookList = bookDao.selectAll();
		model.addAttribute("BOOKS", bookList);
		model.addAttribute("BODY", "BOOK-LIST");
		return "home";
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET) //get방식은 메뉴클릭했을 때 접근하는 방식,post는 input box에 입력하고 전송눌렀을때 submit되는방식
	public String input(@ModelAttribute("bookVO") BookVO bookVO,Model model) {

	

		model.addAttribute("BODY", "BOOK-WRITE");
		model.addAttribute("bookVO", bookVO);
		return "home";
	}

	/*
	 * Spring form taglib를 사용하여 입력 write form을 만들엇을 경우에는 VO 클래스, 객체를 매개변수로 사용할
	 * 때 @modelattribute("VO") 를 필수로 사용하자
	 */

	@RequestMapping(value = "/input", method = RequestMethod.POST)
	public String input(@ModelAttribute("bookVO") BookVO bookVO, SessionStatus status) {

		log.debug(bookVO.toString());
		int ret = bookDao.insert(bookVO);

		if (ret < 1) {
			// insert가 실패했다는 뜻으로 그에 대한 메시지를 보여주는 페이지로 jump
		}
		
		// sessionattributes를 controller 에 설정햇을 경우 입력박스에 담긴 값을 post받아 DB에 반영한 후
		// 에는 반드시 sessionstatus.setcomplete() method를 호출해서 session을 clear해 주어야 한다.
		// 그렇지 않으면 한번 입력한 내용이 계속해서 입력창에 나타난다
		
		status.setComplete();
		return "redirect:/books";
	}
	
	//localhost:8080/book/books/detail/3 이라고 request가 오면 맨 끝에 숫자 3을 mapping 주소의 {book_seq}위치에
	//mapping한다 
	// 매개변수에 설정된 PathVariable에 따라 String id 변수에 3의 값이 할당되어 method에 전달된다. 
	
	
	@RequestMapping(value = "/detail/{book_seq}",method=RequestMethod.GET,
	         produces = "application/json;charset=utf8")
	   public String detail(@PathVariable("book_seq")
	   String id, Model model) {
	      
	      log.debug("PATH : {}",id);
	      long seq = Long.valueOf(id);
	      BookVO bookVO = bookDao.findById(seq);
//	      log.debug(bookVO.toString());
	      
	      model.addAttribute("BOOKVO", bookVO);
	      
	      
	      // 09-28 추가
	      LocalDateTime lDateTime = LocalDateTime.now();
	      String lDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(lDateTime);
	      String lTime = DateTimeFormatter.ofPattern("HH:MM:SS").format(lDateTime);
	      
	      // 09-28 추가
	      ReadBookVO readBookVO = ReadBookVO.builder()
	            .r_date(lDate)
	            .r_stime(lTime)
	            .build();
	      
	      List<ReadBookVO> readList = rbookDao.findBySeq(seq);
	      // 09-28 추가
	      
	      model.addAttribute("READ_BOOK",readList); //readbook-write에 있는 items에 readList 담아 보낸다 
	      model.addAttribute("readBookVO",readBookVO);
	      
	      
	      model.addAttribute("BODY", "BOOK-DETAIL");
	      return "home";
	   }
	
	@RequestMapping(value="/delete/{seq}", method=RequestMethod.GET)
	public String delete(@PathVariable("seq") String seq,
						@ModelAttribute("bookVO") BookVO bookVO) {
		
		long id = Long.valueOf(seq);
		bookDao.delete(id);
		
		return "redirect:/books";
	}
	
	@RequestMapping(value="/update/{seq}", method=RequestMethod.GET)
	public String delete(@PathVariable("seq") String seq,
						@ModelAttribute("bookVO") BookVO bookVO,Model model) {
		
		long id = Long.valueOf(seq);
		bookVO =bookDao.findById(id);
		
		model.addAttribute("bookVO", bookVO);
		model.addAttribute("BODY","BOOK-WRITE");
		
		return "home";
	}
	
	@RequestMapping(value="/update/{seq}", method=RequestMethod.POST)
	public String delete(@PathVariable("seq") String seq,
						@ModelAttribute("bookVO") BookVO bookVO,Model model, SessionStatus status) {
		
		bookDao.update(bookVO); 
		status.setComplete();
		return "redirect:/books/detail/" +seq;
	}
}











package com.biz.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.blog.model.BlogVO;
import com.biz.blog.service.BlogService;

import lombok.extern.slf4j.Slf4j;



@Slf4j //롬복을 사용하여 slf4j 와 logback연동하고 로그를 사용할 수 있도록 설정하라 
@RequestMapping(value = "blog")
@Controller
public class BlogController {

	// @Service Annotation이 부착된 클래스를 주입해 달라
	@Autowired
	
	// 1개의 인터페이스를 상속받은 클래스가 2개 이상일 때 어떤 클래스를 가져와서 autowired를 할 지 명시해준다. 
	@Qualifier("bServiceV2")
	private BlogService bService;

	
	// http://localhost:8080/blog/blog/list 주소로 리퀘스트 했을 때 응답할 함수
	//method=RequestMethod.GET : req할 때 a href =주소를 클릭했을 때 응답하는 요청 메서드 
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<BlogVO> blogList = bService.selectAll();
		//System.out.println(blogList.get(0).getBl_title());
		model.addAttribute("BLOGS", blogList);

		return "list";
	}

	// 매개변수로 Model 을 받는다
	// 한개의 데이터만 받는 method
	@RequestMapping(value = "/getblog", method = RequestMethod.GET)
	public String getBlog(Model model) {

		System.out.println("여기는 블로그 보기!!!");

		List<BlogVO> blogList = bService.selectAll();
		if (blogList != null) {
			int size = blogList.size();

			// size - 1 값이 0부터 시작하므로 전체 리스트에서 -1 을 해주어야
			// 가장 최근에 저장한 마지막 값이 저장되기 때문이다.
			model.addAttribute("TITLE", blogList.get(size - 1).getBl_title());
			model.addAttribute("CONTENT", blogList.get(size - 1).getBl_contents());
			model.addAttribute("USER", blogList.get(size - 1).getBl_user());
		} else {
			model.addAttribute("TITLE", "데이터가 없음");
		}
		return "view";
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(Model model) {

		// write.jsp 에서 input POST로 데이터를 전달할 때 비어있는 ("")데이터 때문에 발생하는 400 오류를 방지하기 위해
		// 공백의 새로운 BlogVO만들어서 write.jsp로 보내준다. 
		model.addAttribute("BLOG", new BlogVO());
		return "write";
	}

	/*
	 * @ModelAttribute form에서 input 에 입력한 문자열이 전송되어 오면 input tag의 변수(name)을 분석하여 VO
	 * class의 필드변수와 일치하면 전달된 데이터(값)을 VO객체에 담아달라
	 * 
	 */
	@RequestMapping(value = "/input", method = RequestMethod.POST)
	public String write(@ModelAttribute BlogVO blogVO, Model model) {

		// 폼에서 건너온 데이터가 정확히 vo에 담겨있나??? 확인하기 위해서
		// 사용하는 코드 아래 네줄!
		// 이 코드는 프로젝트 수행과는 아무런 관련이 없는 코드이다. 
		// 확인하는 코드(디버깅 코드)
		log.debug("USER :" + blogVO.getBl_user());
		log.debug("TITLE :" + blogVO.getBl_title());
		log.debug("CONTENT :" + blogVO.getBl_contents());
		log.debug("로그인한 사용자는?"+"홍길동");
		log.debug("로그인한 비밀번호는?"+"1234");
		
		
		bService.insert(blogVO);

		model.addAttribute("TITLE", blogVO.getBl_title());
		model.addAttribute("USER", blogVO.getBl_user());
		model.addAttribute("CONTENT", blogVO.getBl_contents());

		// 우리가 데이터를 입력한 후에 다시 리스트로 돌아가는 return문을 만들고 싶다.
		return "redirect:/blog/list";

	}
	
	/*
	 * @RequestMapping(value = "/writer1", method=RequestMethod.GET) public String
	 * write1() { // return이 null 값으면 writer1을 찾는다 return "null"; }
	 */
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(String seq, Model model) { // 이 매개변수 통해 리스트에서 view함수 호출하는 데 seq
		
		log.debug("SEQ {}", seq);
		
		/*
		 * 
		 * SQL injection 공격을 사전에 차단하기 위해 컨트롤러에서 SEQ값을 문자열 형에서 long 형으로
		 * 변환하는 코드를 추가
		 */
		long long_seq =0;
		try {
			long_seq =Long.valueOf(seq);
		} catch (Exception e) {
			model.addAttribute("ERROR",seq+"형식의 query금지!");
			return "view_error";
		}
		
		BlogVO blogVO = bService.findBySeq(long_seq);
		model.addAttribute("BLOG",blogVO);
		return "view";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String seq) {
		
		
		long long_seq =0;
		try {
			long_seq= Long.valueOf(seq);
		} catch (Exception e) {
			return "view_error";
		}
		bService.delete(long_seq);
		return "redirect:/blog/list";
		
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(String seq, Model model) {
		
		long long_seq=0;
		
		try {
			long_seq=Long.valueOf(seq);
		} catch (Exception e) {
			return "view_error";
		}
		// update할 데이터를 select 해 오기
		BlogVO blogVO = bService.findBySeq(long_seq);
		
		// update할 데이터를 model 에 싣기
		model.addAttribute("BLOG",blogVO);
		
		// 입력폼 화면 열기
		return "write";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(BlogVO blogVO, Model model) {
		
		// 로그 쓸 때는 반드시 tostring 붙여주기 
		log.debug("UPDATE POST Method");
		log.debug(blogVO.toString());
		
		// 수정이 완료되면 다시 detail view로 화면을 전환하기 
		bService.update(blogVO);
		model.addAttribute("seq", blogVO.getBl_seq());
		
		return "redirect:/blog/view";
	}

}

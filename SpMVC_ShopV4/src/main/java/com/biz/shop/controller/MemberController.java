package com.biz.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.shop.model.MemberVO;
import com.biz.shop.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value="/member")
@Controller
public class MemberController {
	
	@Qualifier("memServiceV1")
	@Autowired
	private MemberService memService;

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute("MEM_VO") MemberVO memVO, Model model) { // mem_vo이름으로 모델에 넘겨줌!!
		
		// @ModelAttribute("MEM_VO") MemberVO memVO, Model model 매개변수가
		// MemberVO memVO = new MEMberVO();
		// model.addAttribute("MEM_VO"), memVO) 두 문장을 대신한다. 
		model.addAttribute("BODY","MEM_WRITE");
		return "home";
	}
	
	// to {GET /member/join}: There is already 'memberController' bean method
	// mapper가 같은데 여기도 get방식으로 하면 누구를 선택할지 몰라 뜨는 오류
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute("MEM_VO") MemberVO memVO, Model model, String s) {
		
		log.debug(memVO.toString());
		
		memService.insert(memVO);
		return "redirect:/"; 
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(@ModelAttribute("LOGIN_VO") MemberVO loginVO, Model model) {
		
		model.addAttribute("BODY","LOGIN");
		return "home";
	}
	
	/*
	 * 보안 관련된 개념
	 * 인증 : 아이디 패스워드 등을 검사하여 정상 사용자 인가를 알아보는 것
	 * 인가 : 인증이 성공하면 정상 사용자 라는 것을 확인시키는 것
	 * 권한 : 인가받은 사용자의 권한이 어떠한 것인가
	 * 
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@ModelAttribute("LOGIN_VO") MemberVO loginVO, Model model,HttpSession httpSession) {
		
		// 로그인을 위한 입력값 
		log.debug(loginVO.toString());
		
		MemberVO memVO =memService.login(loginVO);
		
		// 로그인 체크를 수행한 후의 사용자 정보 
		//log.debug(memVO.toString());
		
		String retURL ="";
		if(memVO ==null) {
			retURL = "LOGIN";
			model.addAttribute("MSG","아이디가 없습니다!");
		}else if(!loginVO.getM_userid().equals(memVO.getM_userid())) { //패스워드 fail 성공
			retURL = "LOGIN";
			model.addAttribute("MSG","비밀번호가 일치하지 않습니다!");
		}else { //인증이 된 경우
			/*
			 * HttpSession 사용하여 클라이언트와 서버 간에 세션을 주고 받을 수 있도록
			 * 하는 절차!
			 * 
			 */
			httpSession.setAttribute("LOGIN", memVO); //여기만 setattribute 
			// addattribute는 메모리도 많이 잡아먹고 로그아웃전까지 계속 저장되기 때문에 보안에 취약하다
		}
		model.addAttribute("BODY",retURL);
		return "home";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession httpSession) {
	
		httpSession.removeAttribute("LOGIN");
		httpSession =null;
		return"home";
	}
}














package com.biz.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.book.model.UserDetailsVO;
import com.biz.book.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value="/member")
@Controller
public class MemberController {

	private final MemberService memberService;
	/*
	 * VO 클래스를 controller의 매개변수로 설정하고 @modelAttribute("이름") 설정했을 경우
	 * 1. form 에서 post로 데이터를 보냇을 경우 form 에서 보낸 데이터가 담긴 vo객체를 생성하여 method 내의 코드에서
	 *    사용할 수 있도록 준비해준다
	 * 2. 아무도(아무곳에서도) 객체의 필드변수와 일치하는 변수를 전달하지 않을 경우 자체적으로 vo 클래스의 생성자를 호출하여 비어있는
	 * 		객체를 만들어서 method 내에 코드에서 사용할 수 있도록 준비해둠
	 * 
	 */
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute("memberVO") UserDetailsVO userVO, Model model) {
		
		// modelattribute("memberVO") 라는 어노테이션 이용하여 UserDetailsVO userVO = new UserDetailsVO(); 대신함
		
		model.addAttribute("memberVO", userVO); //매개변수 주입
		
		model.addAttribute("BODY","MEMBER-JOIN");
		return "home";
	}
	
	@RequestMapping(value="/join", method= RequestMethod.POST)
	public String join(@ModelAttribute("memberVO") UserDetailsVO userVO) {
		
		memberService.insert(userVO);
		
		return "redirect:/";
	}
	
	
	
	// logout.jsp 파일을 보여주기 위한 url mapping
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		return "member/logout";
	}
}

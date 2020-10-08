package com.biz.book.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.book.model.UserDetailsVO;
import com.biz.book.service.MemberService;

import lombok.RequiredArgsConstructor;


// @sessionAttributes("memberVO")항목 설정하면
// 서버의 메모리에 memberVO 이름으로 객체변수를 마련해 놓는다.
// 이 변수는 서버가 재 시작하거나 하더라도 유지가 되는 성질이 있고
// 클라이언트에서 request를 수행했을 때 requestmapping method에 @modelattribute("객체이름") 클래스 객체 형식으로
// 매개변수가 있으면 메모리에 저장된 객체변수에서 값을 추출하여
// 객체를 포함해 준다.


@SessionAttributes("memberVO") // 세션어노테이션 있는데 클래스가 시작되는 부분에 modelattribute어노테이션이 붙은 메서드 없으면 컴파일 오류 남 
@RequiredArgsConstructor
@RequestMapping(value="/member")
@Controller
public class MemberController {

	private final MemberService memberService;
	
	// @sessionattributes(memberVO)를 사용하려면
	//반드시 memberVO를 생성하는 method가 클래스에 있어야 한다.
	// userdetailvo 클래스로 생성된 memberVO가 "memberVO" 이름을 보관된다. 
	
	@ModelAttribute("memberVO") // 세션에 붙으면 modelattribute가 항상 따라다녀야 함
	public UserDetailsVO newMember() {
		
		UserDetailsVO memberVO = new UserDetailsVO(); //userdetailsVO.builder().build();
		return memberVO;
	}
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
	
	/*
	 * 회원가입 입력폼을 2개로 분리하여 사용하기 위해
	 * join-get : member-write.jsp 가 열리고 
	 * join post : member-write2.jsp가 열린다
	 * member-write.jsp에서 입력한 id, 비밀번호를 join post로 보내면 
	 * @modelattribute("memberVO") 설정을 확인하고 server임시 보관중인 sessionAttributes("memberVO")를 찾아서
	 * 입력박스로부터 전달된 데이터를 보관한다.
	 * 
	 *  member-write2.jsp 열고 나머지 데이터를 입력 한 후 join-comp post로 보내면
	 *  먼저 입력받아서 sessionAttributes에 보관중인 id,비번과 나중에 입력한 이름, 전화번호 등등과 함께
	 *  묶어서 join_comp userVO에 담아준다
	 *  입력폼의 항목이 매우 많을 때 입력폼을 분리해서 코딩을 해도 sessionAttributes의 성질을 이용하여
	 *  마치 입력마법사와 같은 기능을 구현할 수 있다. 
	 * 
	 */
	@RequestMapping(value="/join", method= RequestMethod.POST)
	public String join(@ModelAttribute("memberVO") UserDetailsVO userVO, Model model, String s) {
		
		model.addAttribute("memberVO", userVO);
		model.addAttribute("BODY","MEMBER-JOIN-NEXT");
		
		return "home";
	}
	
	/*
	 * @sessionattribute()를 사용할 때눈
	 * DB에 데이터를 insert, update 최종수행하고 나면
	 * sessionstatus 클래스의 setComplete() method 를 호출하여 서버에 남아잇는 메모리를 clear 해 주어야 한다.
	 * 
	 */
	@RequestMapping(value="/join_comp", method= RequestMethod.POST)
	public String join(@ModelAttribute("memberVO") UserDetailsVO userVO, SessionStatus status) {
		
		memberService.insert(userVO);
		status.setComplete();
		
		return "redirect:/";
	}
	
	
	
	
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String mypage(@ModelAttribute("memberVO") UserDetailsVO userVO, Authentication authProvider, Model model) {
		
		// 현재 로그인한 사용자의 정보를 추출하는 method (Authentication)
		// spring security를 통과하여 로그인이 인가된 사용자의 정보는 
		// 현재 method가 호출될 때 spring security의 필터 체인에 의해서 메서드의 매개변수로 설정된
		// Authentication 클래스로 선언된 객체에 담겨서 전달이 된다. 
		// 객체서 getPrincipal() method를 호출하여 데이터를 UserDetailsVO의 userVO 객체에 담아서 일반 userVO(memberVO)
		// 처럼 취급하여 사용할 수 있다. 
		
		
		userVO = (UserDetailsVO) authProvider.getPrincipal();
		userVO.setPassword("");
		
		model.addAttribute("memberVO",userVO);
		model.addAttribute("BODY","MEMBER-UPDATE");
		
		return "home";
				
	}
	
	@RequestMapping(value="/mypage", method= RequestMethod.POST)
	public String mypage(@ModelAttribute("memberVO") UserDetailsVO userVO, Model model, String s) {
		
		model.addAttribute("memberVO", userVO);
		model.addAttribute("BODY","MEMBER-UPDATE-NEXT");
		
		return "home";
	}
	
	
	@RequestMapping(value="/update_comp", method= RequestMethod.POST)
	public String update(@ModelAttribute("memberVO") UserDetailsVO userVO, SessionStatus status) {
		memberService.update(userVO);
	
		status.setComplete();
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value="/password_check", method=RequestMethod.POST)
	public String password_check(String username, String password) {
		
		return memberService.userNameAndPassword(username, password);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/id_check", method=RequestMethod.POST)
	public String id_check(String username) {
		
		// TDD(test driven developer) 컨트롤러에서 먼저 만들고 나중에 메서드 만듦
		// memberService에 아직 구현되지 않은 method를 사용처에서 먼저 만들고 
		// 문법오류가 발생하면 구체적으로 memberService method를 구현하는 방법
		UserDetailsVO userVO = memberService.findById(username);
		
		if(userVO ==null) { //아직 회원가입이 안됨= username이 DB에 없다
			return "OK";
		} else {
			return "FAIL";
		}
				
	}
	
	// logout.jsp 파일을 보여주기 위한 url mapping
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		return "member/logout";
	}
	
	@ResponseBody
	@RequestMapping(value="/user_info", method=RequestMethod.GET)
	public UserDetailsVO userInto(Principal principal, 
			Authentication authentication, @ModelAttribute("memberVO") UserDetailsVO userVO, Model model) {
		
		// spring security 프로젝트에서 로그인한 사용자정보를 추출하는 여러가지 방법
		// 1. UserDetailsServiceImplV1에서 공급받는 방법 (완벽보안은 아님)
		//    토큰 가져와서 getPrincipal 하는 방법
		//		서버의 session memory에 직접 접근하여 사용자정보를 추출하는 방법으로 보안에 상당히 취약 사용지양
		
		//UsernamePasswordAuthenticationToken upa= (UsernamePasswordAuthenticationToken) principal; // 이거쓸때는 Principal 클래스 이용->형변환
		
		//userVO = (UserDetailsVO) upa.getPrincipal(); // 
		
		
		// 2. SecurityContextHolder 로부터 추출하는 방법
		//userVO =(UserDetailsVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		// 3. Authentication 클래스를 매개변수로 설정
		// @autnenticationPrincipal 이 작동이 안되는 관계로 매개변수에 @authentication클래스 객체로 선언하고
		// authentication.getPrincipal() method 호출하여 userVO 추출
		userVO = (UserDetailsVO) authentication.getPrincipal();
		
		return userVO;
	}
	
	
	
	
	
	
	
	
	
	
	
}

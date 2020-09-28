package com.biz.book.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.book.mapper.AuthorityDao;
import com.biz.book.mapper.UserDao;
import com.biz.book.model.UserDetailsVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("memberServiceV1")
@RequiredArgsConstructor
public class MemberServiceImplV1 implements MemberService{

	// MemberServiceImplV1 클래스의 필드변수가 잇는 생성자를 자동으로 만들고 
	// private final 로 선언된 객체에 @autowired를 설정하고 container로부터 객체를 주입받아 사용하게 만들어주는
	// lombok의 어노테이션
	
	//Member
	@Qualifier("passwordEncoder")
	private final PasswordEncoder passwordEncoder;
	
	// 회원정보 CRUD 구현
	private final UserDao userDao;
	
	// 회원의 권한 정보 CRUD 구현
	private final AuthorityDao authDao;
	
	// 컨트롤러에서 회원정보를 전달받아서 비밀번호를 암호화 하고 
	// userDao 에 보내서 저장하기
	// 사용자의 role정보를 생성하여 authorityVO 에 담고 저장하기 
	public int insert(UserDetailsVO userVO) {
		
		String password = userVO.getPassword();
		String encPassword = passwordEncoder.encode(password);
		log.debug("password {} , encPassword {}", password, encPassword);
		
		userVO.setPassword(encPassword);
		userDao.insert(userVO);
		
		return 0;
	}
	
	
	
	
	
	
	
	
}

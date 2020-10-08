package com.biz.book.service.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biz.book.mapper.AuthorityDao;
import com.biz.book.mapper.UserDao;
import com.biz.book.model.AuthorityVO;
import com.biz.book.model.UserDetailsVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * spring security 프로젝트에서 사용자 인가와 권한을 관리하는 클래스!
 * UserDetailsService 를 커스터마이징 
 * 
 * 커스터마이징
 * 패키지형 솔루션 가지고 있는 it회사에서 어떤회사에 솔루션을 판매하면서 회사의 실정,
 * 업무환경, 여러 여건들을 요구분석하여 솔루션 사용하는 회사에 최적하 하는 것
 * 
 * context(service패키지)에서 한번, servlet(auth패키지)에서 한번 스캔 두번됨
 */
@Slf4j
@RequiredArgsConstructor
@Service("userDetailServiceV1")
public class UserDetailServiceImplV1 implements UserDetailsService{
	
	/*
	 * 그동안 @autowired 사용하여 객체 주입받아서 사용해 왔는데 @autowired로 주입받은 객체에
	 * 메모리 누수 현상이 발생햇다. 
	 * 
	 *  주입받을 객체를 final로 선언 해주는데 final로 선언한 객체는 반드시 생성자에서 객체 초기화(주입)를 해야한다.
	 *  
	 *  1. 주입받을 객체를 final로 선언하고 
	 *  2. 생성자의 매개변수를 통하여 객체를 초기화 한다. 
	 *  3. 주입받을 객체의 개수가 늘거나 줄면 생성자를 또 다시 변경해야 하는 번거로움이 있다. 
	 *  
	 *  그래서 롬복의 @RequiredArgsConstructor를 이용하여 final로 선언된 모든 필드변수들을 모아서 생성자로 만들어준다.  
	 * 
	 */

	private final UserDao userDao;
	private final AuthorityDao authDao;
	
	/*
	 * 이 프로젝트에서 사용할 멤버(user) 관련 테이블에서 username으로 사용자 정보를
	 * select 하고 사용자의 roll 정보를 기준으로 사용자의 권한을 설정하여 기능 수행을 제한하는 설정을 하고
	 * 사용자의 여러 세부정보를 VO객체에 담아주는 역할 수행한다. 
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetailsVO userDetail =userDao.findById(username);
		
		
		// 테스트를 위한 임시 사용자 정보 생성
		/*userDetail = UserDetailsVO.builder().username(username).password("12341234").Enabled(true).build();*/
		
		
		if(userDetail == null) { //사용자 정보가 없으면
			
			// 강제로 일부러 usernamenotfoundexception을 발생시킨다
			throw new UsernameNotFoundException(username + "정보를 찾을 수 없음");
		}
		log.debug("USER:" +userDetail.toString());
		
		// 사용자 정보테이블에서 enable 칼럼의 값이 boolean 형으로 설정하고 
		// 최초에 회원가입 했을 때 이 값을 false로 세팅하고 
		// email 인증과 같은 절차를 통과했을 때 이 값을 true로 만들어서 로그인이 될 수 있도록 설정
		
		
		
		//userDetail.setEnabled(true); //이걸 막으면 두번째 사용자는 enabled가 0으로 되어 false , 사용 불가가됨
		// 인증절차 통과했을 때 true로 만들어서 로그인 할 수있도록 해라 
		
		if(this.getAuthorities(username).size()==0) { // 권한이 없으면?
			throw new UsernameNotFoundException(String.format("[ %s ]는 아무런 권한이 없습니다", username));
		}
		
		// DB로부터 가져와서 GrantedAuthorrity로 변환한 role 정보를 list userdetails에 저장해라 
		userDetail.setAuthorities(this.getAuthorities(username));
		return userDetail;
	}
	
	// tbl_authority로부터 role 정보를 가져와서 list로 생성
	private Collection<GrantedAuthority> getAuthorities(String username){
		
		
		// tbl_authority로부터 role 정보를 selec
		List<AuthorityVO> authList = authDao.findByUserName(username);
		
		// 변환
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(AuthorityVO vo : authList) {
			
			// 문자열로 된 role 정보를 grantedAuthority 타입으로 변환하여
			// 리스트로 생성하기
			// simplegrantedauthority 클래스를 사용하여 변환
			authorities.add(new SimpleGrantedAuthority(vo.getAuthority()));
		}
		
		return authorities;
	}

}









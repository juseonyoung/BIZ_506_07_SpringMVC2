package com.biz.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.shop.model.MemberVO;
import com.biz.shop.persistence.MemberDao;
import com.biz.shop.service.MemberService;

@Service(value="memServiceV1") // 서비스 이름 써주기..
public class MemberServiceImplV1 implements MemberService{
	
	
	@Autowired
	private MemberDao memDao;

	@Override
	public List<MemberVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * memberService의 insert는 회원가입 join method를 대신한다.
	 * 회원가입 정책(policy)
	 * 1. tbl_member table에 아무런 데이터도 없이 최초로 가입(insert)된 회원정보는 권한(roll)을 admin으로 부여한다.
	 * 2. 두번째부터 가입된 회원은 권한(roll)을 user로 부여한다. 
	 * 3. 회원이 탈퇴를 요청하면 권한(roll)을 "guest"로 변경한다.
	 * 4. 같은 아이디로 재 가입을 요청하면 권한을 guest에서 user로 변경한다.
	 * 5. admin 권한은 현재 app의 모든 기능을 사용할 수 있다. 
	 * 		- 다른 사용자의 정보를 변경할 수 있다.
	 * 		- 다른 사용자의 권한(roll)을 변경할 수 있다.
	 * 6. user 권한은 admin 권한으로 접근할 수 있는 부분은 사용이 불가하다. 
	 * 		- 본인의 사용자 정보만 변경할 수 있다.
	 * 		- 본인의 사용자 권한은 변경할 수 없다.
	 * 
	 * 7. guest 권한은 로그인과, 재가입 요청만 할 수 있다.
	 * 
	 * * 회원가입 절차
	 * 1. tbl_member table에 회원정보가 1개라도 있는지 먼저 검사
	 * 2. 없으면-> 현재 추가할 회원은 admin
	 * 3. 있으면 -> 현재 추가할 회원은 user
	 * 4. memberVO의 ROLL 칼럼에 권한 문자열 추가하고 
	 * 5. insert 수행하자
	 * 
	 *  
	 * 
	 */
	@Override
	public int insert(MemberVO membVO) {
		
		// tbl_member table에 저장된 레코드 개수 가져오기
		int count = memDao.memberCount();
		
		/*
		 * if(count ==0){
		 * 	membVO.setM_roll("ADMIN")
		 * }
		 * 테이블에 데이터가 하나도 없을 때는 ADMIN으로 설정한다는
		 * 이 비교 연산식은 문법적, 논리적으로 아무런 문제가 없는 코드이다.
		 * 
		 * 하지만 실제상황에서 JDBC를 통해 서버에 원격연결을 하는 상황에서는 
		 * 카운트 값이 실제로는 0이지만 0이 아닌 -1 등ㄱ의 값이 될 수도 있다.
		 * 데이터가 없을 때 0이거나 -1인 경우가 된다면 
		 * if(count ==0 || count == -1 ) 또는 
		 * if(count <= 0) 과 같은 비교연산식이 만들어 져야 한다.
		 * 
		 * 비교연산식에서 가장 권장하는(좋은 코드)는 가급적 비교대상을 부등호로 한번만 비교하는 것!
		 * else가 있는 상황이므로 더 값을 근접하게 찾는 연산식으로 변경하는 것이 좋다.
		 * 
		 * if(count >0)이 이상적인 코딩이다. 
		 * 
		 */
		// 회원정보가 없으면 admin, 있으면 user
		if (count >0) {
			membVO.setM_roll("USER"); //데이터가 1개라도 있으면 ㅈ무조건 0보다 크다
		} else {
			membVO.setM_roll("ADMIN"); //데이터가 없거나 -1값이 리턴되는 경우도 있다. 
		}
		
		int ret =memDao.insert(membVO);
		
		return 0;
	}

	@Override
	public int update(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * 로그인 절차
	 * 1. 로그인 VO에 담긴 정보에서 user ID를 추출하여 사용자 정보를 가져오기 
	 *     findByID()
	 * 2. 결과값이 null이면 controller로 즉시 null값 리턴 :login fail했다는 뜻
	 * 3. 결과값이 null이 아니면 password 비교 
	 * 4. 패스워드 일치하면 로그인 ok-> memberVO를 컨트롤러로 리턴
	 * 5. 		   일치하지 않으면 password fail! -> 컨트롤러로 패스워드 fail 알림! 중요!! 
	 * 
	 */
	@Override
	public MemberVO login(MemberVO loginVO) {
		MemberVO memVO = memDao.findById(loginVO.getM_userid());
		
		if(memVO !=null) {
			if(!loginVO.getM_password().equals(memVO.getM_password())) {
				memVO.setM_roll("P_FAIL");
				memVO.setM_userid("P_FAIL");
			}
		}
		return memVO;
	}

}

package com.biz.bbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biz.bbs.model.BBsVO;
import com.biz.bbs.service.BBsService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin({"http://127.0.0.1:5500", "https://naver.com" , "*"}) //여기서 요청한 건 무시해라
@Slf4j
@RestController
@RequestMapping(value="/api")
public class BBsAPIController {

	@Autowired
	@Qualifier("bbsServiceV1")
	private BBsService bbsService;
	
	@CrossOrigin("http://127.0.0.1:5500") // 이 주소에서 아래 메서드를 호출하면(api 요청하면) 
	// cors policy 를 무시하고 응답하라! post로 보내짐
	
	
	// localhost:8080/bbs/api/bbs 요청을 하면 게시판 list를 보내달라(getter)
	@RequestMapping(value="/bbs", method=RequestMethod.GET)
	public List<BBsVO> bbs_list() {
		
		List<BBsVO> bbsList = bbsService.selectAll();
		
		return bbsList;
	}
	
	//localhost:8080/bbs/api/bbs/3 요청을 하면 게시판 데이터 중에서 1개를 보내달라(getter)
	@RequestMapping(value="/bbs/{seq}",method=RequestMethod.GET)
	public BBsVO  bbs_item(@PathVariable("seq") String seq) {
		
		long long_seq = Long.valueOf(seq);
		BBsVO bbsVO = bbsService.findBySeq(long_seq);
		
		return bbsVO;
	}
	
	
	// form 에 데이터를 입력하고 submit수행하면 데이터를 수신하여 insert하라
	// VO 클래스를 매개변수로 지정하고 데이터를 수신한다. 
	// @modelattribute 를 사용하여 폼에서 submit한 데이터를 VO에 담기
	// 4.x 이상에서는 @modelattribute annotation을 생략해도 form의 input 태그에 지정된 네임값과 같은 구조를 가진 VO를
	// 매개변수로 설정하면 자동으로 @modelattribute에 지정한것과 같은 효과를 낸다. 
	@RequestMapping(value="/bbs", method=RequestMethod.POST)
	public String bbs_insert(BBsVO bbsVO) {
		
		log.debug("POST requestmethod type으로 요청된 메소드");
		log.debug("BBSVO {}", bbsVO.toString());
		
		return "bbs_insert";
	}
	
	// form 에 데이터를 입력하고 submit수행하면 데이터를 update하라
	@RequestMapping(value="/bbs", method=RequestMethod.PUT)
	public String bbs_update(@ModelAttribute BBsVO bbsVO) {
		
		log.debug("PUT requestmethod type으로 요청된 메소드");
		log.debug("수신 데이터 {}", bbsVO.toString());
		return "bbs_update";
	}
	
	// 특정한 게시판 데이터를 삭제하라
	// 삭제할 게시판의 id(seQ)값 1개만 받으면 된다.
	// id값 1개를 받기 위해서 VO 를 사용하는 것은 왠지 낭비...
	// 1개의 String형 변수만 받아도 되는데, 문제는 front에서 ajax로 데이터를 보낼 때 
	// 보내는 방식에 따라서 문자열형 변수로 보낼 수 있고
	// json 형태로 보낼 수도 있다. 
	
	// 문자열 형으로 보내면 String id 와 같이 받으면 되는데 
	// json형식으로 보냈을 때는 약간의 문제를 일으킨다..
	// 이유는 Spring이 태생적으로 json 을 지원하지 않기 때문 
	
	// 그래서 사용하는 방법 Map<String, String> 형식 매개변수 만들어주고
	// @requestbody를 붙여주는 방식을 사용 리퀘스트 바디는 절대 사용할 수 없다
	
	@RequestMapping(value="/bbs", method=RequestMethod.DELETE)
	public String bbs_delete(@RequestBody Map<String, String> data) {
		
		log.debug("DELETE Requestmethod Type으로 요청된 method입니다");
		return "bbs_delete";
	}
	
}



















package com.biz.ems.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.biz.ems.model.EmsVO;
import com.biz.ems.service.EmsService;
import com.biz.ems.service.FileService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin({"http://127.0.0.1:5500", "https://naver.com" , "*"})
@Slf4j
@RestController
@RequestMapping(value="/api")
public class EmsAPIController {

	@Autowired
	@Qualifier("emsServiceV1")
	private EmsService emsService;
	
	
	@Autowired
	@Qualifier("fileService") //버전 몇으로??
	private FileService fileService;
	
	@CrossOrigin("http://127.0.0.1:5500")
	
	
	@RequestMapping(value="/ems", method=RequestMethod.GET)
	public List<EmsVO> ems_list(){
		
		List<EmsVO> emsList = emsService.selectAll();
		
		return emsList;
	}
	
	@RequestMapping(value="/bbs/{seq}", method=RequestMethod.GET)
	public EmsVO ems_item(@PathVariable("seq") String seq) {
		
		long long_seq =Long.valueOf(seq);
		EmsVO emsVO = emsService.findBySeq(long_seq);
		
		return emsVO;
	}
	
	@RequestMapping(value="/ems", method=RequestMethod.POST)
	public String ems_insert(@ModelAttribute EmsVO emsVO, @RequestParam("file")
	MultipartFile file) {
		
		log.debug("POST Type으로 요청된 메소드입니다");
		log.debug("emsVO {}",emsVO.toString());
		log.debug("수신한 파일정보 : {}", file.getOriginalFilename());
		
		return "ems_insert";
	}
	
	// 데이터 입력하고 서브밋하면 update
	@RequestMapping(value="/ems", method=RequestMethod.PUT)
	public String bbs_update(@ModelAttribute EmsVO emsVO, @RequestParam("file")
	MultipartFile file) {
		
		log.debug("PUT Type 으로 요청된 메소드");
		log.debug("수신한 데이터 : {}",emsVO.toString());
		log.debug("수신한 파일정보 : {}",file.getOriginalFilename());
		
		return "ems_update";
	}
	
	@RequestMapping(value="/ems", method=RequestMethod.DELETE)
	public String ems_delete(@RequestBody Map<String, String> data) {
		
		log.debug("DELETE Type으로 요청된 메소드입니다");
		return "ems_delete";
		
	}
	
	
	@RequestMapping(value="/file", method=RequestMethod.POST)
	public String file_up(@RequestParam("file") MultipartFile file) {
		
		String ret_file = fileService.fileUp(file);
		return ret_file;
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

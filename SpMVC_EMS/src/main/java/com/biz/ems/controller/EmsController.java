package com.biz.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.ems.model.EmsVO;
import com.biz.ems.service.EmsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping(value="/ems")
public class EmsController {

	@Autowired
	@Qualifier("emsServiceV1")
	private EmsService emsService;
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		
		List<EmsVO> emsList = emsService.selectAll();
		model.addAttribute("EMS_LIST", emsList);
		
		return "ems/list";
	}
	
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String insert() {
		
		return "ems/write";
	}
	
	// 파일 받는 method
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(EmsVO emsVO, @RequestParam(name="file", required=false)
	MultipartFile file, MultipartHttpServletRequest files) {
	
		log.debug("업로드한 파일 이름" +file.getOriginalFilename());
		
		List<String> fileName = emsService.insert(emsVO, files);
		
		return "redirect:/ems/list";
	}
	
	@RequestMapping(value="/detail/{seq}", method=RequestMethod.GET)
	public String detail(@PathVariable("seq") String seq,Model model) {
		
		long long_seq = Long.valueOf(seq);
		EmsVO emsVO = emsService.findBySeq(long_seq);
		
		model.addAttribute("EMSVO", emsVO);
		return "/ems/detail";
	}
	
	
	@RequestMapping(value="/{seq}/{url}", method=RequestMethod.GET)
	public String update(@PathVariable("seq") String seq, @PathVariable("url")
	String url, Model model){
		

		long long_seq = Long.valueOf(seq);
		String ret_url ="redirect:/ems/list";
		
		if(url.equalsIgnoreCase("DELETE")) {
			emsService.delete(long_seq);
		} else if(url.equalsIgnoreCase("UPDATE")) {
			model.addAttribute("EMSVO",emsService.findBySeq(long_seq));
			ret_url = "/ems/write";
			
		}
		return ret_url;
		
	}

	
}

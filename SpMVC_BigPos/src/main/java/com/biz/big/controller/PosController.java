package com.biz.big.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.big.model.DeptVO;
import com.biz.big.model.PosVO;
import com.biz.big.service.PosService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PosController {

	@Qualifier("posServiceV1")
	@Autowired
	private PosService posService;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Model model) {
		List<PosVO> posList = posService.selectAll();
		PosVO posVO = new PosVO();
		
		model.addAttribute("POS_LIST",posList);
		return "home";
	}
	
	@RequestMapping(value="/input", method=RequestMethod.GET)
	public String write(Model model) {
		LocalDateTime ldt = LocalDateTime.now();
		String cd = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(ldt);
		String ct = DateTimeFormatter.ofPattern("HH:mm:ss").format(ldt);
		
		PosVO posVO = PosVO.builder().io_date(cd).io_time(ct).build();
		DeptVO deptVO = new DeptVO();
		model.addAttribute("POS", posVO);
		model.addAttribute("DEPT",deptVO);
		return "pos-write";
	}
	
	@RequestMapping(value="/input",method=RequestMethod.POST)
	public String write(@ModelAttribute PosVO posVO, Model model) {
		
		int ret = posService.insert(posVO);
		model.addAttribute("POS",posVO);
		return "redirect:/";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(String seq, Model model) {
		
		long long_seq = Long.valueOf(seq);
		
		PosVO posVO = posService.findById(long_seq);
		
		model.addAttribute("POS",posVO);
		return "pos-write";
	}
	
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(PosVO posVO) {
	
		int ret = posService.update(posVO);
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

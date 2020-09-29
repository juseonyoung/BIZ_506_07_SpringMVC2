package com.biz.iolist.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.iolist.mapper.ProductDao;
import com.biz.iolist.model.ProductVO;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class ProductController {

	@Autowired
	private ProductDao ProDao;

	// localhost:8080/book/books
	// localhost:8080/book/books/ 슬래스 넣을때나 넣지 않을때나 같은 값 나오도록

	// @ResponseBody //객체를 그대로 json형태로 내려보내라
	

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET) // 문자열배열
	public String list(Model model) {

		List<ProductVO> ioList =ProDao.selectAll();
		model.addAttribute("LIST", ioList);
		model.addAttribute("BODY", "IO-LIST");
		return "home";
	}
}
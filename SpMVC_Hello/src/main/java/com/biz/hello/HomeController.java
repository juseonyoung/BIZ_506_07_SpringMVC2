package com.biz.hello;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	// 사용자로부터 요청을 받아들이는 코드
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
	
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		// serverTime이라는 변수를 만들고 foramttedDate값을 넣어라.
		model.addAttribute("serverTime", formattedDate );
		
		// home으로 return(웹 브라우저로 보내라)
		return "home";
	}

	@RequestMapping(value = "/korea", method = RequestMethod.GET)
	public String home2(Locale locale, Model model) {
	
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		// serverTime이라는 변수를 만들고 foramttedDate값을 넣어라.
		model.addAttribute("serverTime", "Republic of Korea" );
		
		// home으로 return(웹 브라우저로 보내라)
		return "home";
	}

	
}

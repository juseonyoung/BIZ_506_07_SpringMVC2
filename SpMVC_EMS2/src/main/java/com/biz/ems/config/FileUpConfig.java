package com.biz.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/*
 * -context.xml 파일을 대체할 java config
 * 필요한 이름으로 클래스를 선언하고 @configuration annotation을 붙여준다
 * 내부에서 필요한 클래스를 객체로 생성하는 method를 정의하고
 * 객체를 return
 * method에 @bean 붙임
 * 
 * 생성한 클래스를 webconfig에 등록한다
 * 
 */
@Configuration
public class FileUpConfig {

	@Bean //xml bean 체크하는거랑 같은효과
	public MultipartResolver multipartResolver() {
		
		MultipartResolver mr = new CommonsMultipartResolver();
		((CommonsMultipartResolver) mr).setMaxUploadSize(25000000);
		((CommonsMultipartResolver) mr).setMaxUploadSizePerFile(2500000);
		
		return mr;
	}
	
	@Bean(name="winPath")
	public String winPath() {
	
		return "c:/bizwork/workspace/upload/";
	}
	
	@Bean(name="linuxPath")
	public String linuxPath() {
		
		return "/Users/callor/Document/workspace/upload/";
	}
	
	
}














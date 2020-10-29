package com.biz.ems.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class, FileUpConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { ServletConfig.class ,MybatisConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		
		// web.xml 에 있는 필터 부분.......... 캐릭터엔코딩필터
		CharacterEncodingFilter encKor = new CharacterEncodingFilter();
		encKor.setEncoding("UTF-8");
		
		return new Filter[] { encKor };
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.biz.ems.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileService {

	public String fileUp(MultipartFile file); // 1개의 파일
	public List<String> filesUp(MultipartHttpServletRequest files); // 여러개의 파일
	public int fileDelete(String fileName);
	
}

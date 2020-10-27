package com.biz.ems.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.ems.model.ImageVO;

@Service("fileServiceV1")
public class FileServiceImplV1 implements FileService{

	
	public String fileUp(MultipartFile file) {
		
		String fileName = file.getOriginalFilename();
		
		String rootPath =System.getProperty("catalina.home");
		
		File dir = new File(rootPath, "temFolder");
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
	}

	@Override
	public List<ImageVO> fileUp(MultipartHttpServletRequest files) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean fileDelete(String s_file1, String s_file2) {
		// TODO Auto-generated method stub
		return false;
	}
}






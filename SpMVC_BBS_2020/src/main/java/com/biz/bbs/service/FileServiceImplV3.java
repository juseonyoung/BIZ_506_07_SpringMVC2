package com.biz.bbs.service;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("fileServiceV3")
public class FileServiceImplV3 extends FileServiceImplV1{

	
	@Override
	public String fileUp(MultipartFile file) {
		
	
		String rootFolder = "C:/bizwork/workspace/upload"; // 이 폴더에 파일을 저장하겟다
		
		File dir = new File(rootFolder);
		
		
		// file을 업로드할 폴더를 검사하면 없으면 새로 생성해달라
		
		if(!dir.exists()) {
			
			// mkdir()은 제일 끝의 폴더 1개만 생성
			// mkdirs()는 모든 경로에 폴더를 한꺼번에 생성하므로 dirs를 더 많이 사용함 
			dir.mkdirs();
		}
		
		String fileName = file.getOriginalFilename();
		
		// 서버의 저장폴더 +파일이름을 합성하여 파일저장 준비를 함
		
		File saveFile = new File(rootFolder,fileName);
		try {
			file.transferTo(saveFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
}

package com.biz.bbs.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("fileServiceV4")
public class FileServiceImplV4 extends FileServiceImplV1 {

	/*
	 * 필드(맴버)변수를 private final로 선언했을 경우 보통 final로 선언된 변수는 선언과 동시에 생성
	 * (초기화)를 해야한다. 
	 * private final로 선언된 맴버변수는 
	 * 클래스의 생성자 메서드에서 초기화하는 것을 허용한다. 
	 * 
	 * private final로 선언된 맴버변수는 반드시 클래스의 생성자 메서드에서 초기화를 해야한다.
	 * 
	 */
	
	// private 으로 선언된 rootFolder 변수를 protected로 변경
	// protected로 선언된 변수들은 현재 클래스를 상속받은 클래스에서 그대로 사용이 가능하다
	
	protected final String rootFolder;
	public FileServiceImplV4() {
		rootFolder = "C:/bizwork/workspace/upload";
	}
	
	@Override
	public String fileUp(MultipartFile file) {
		
		if(file == null) {
			return null;
		}

		File dir = new File(rootFolder);

		// file을 업로드할 폴더를 검사하면 없으면 새로 생성해달라

		if (!dir.exists()) {

			// mkdir()은 제일 끝의 폴더 1개만 생성
			// mkdirs()는 모든 경로에 폴더를 한꺼번에 생성하므로 dirs를 더 많이 사용함
			dir.mkdirs();
		}

		// 원본 파일이름 된다
		String originalFileName = file.getOriginalFilename();

		// 원본 파일이름을 임의 값을 부착한 변형된 파일이름으로 바꾸기
		// 1. uuid값을 생성하고
		// 2. 원본 파일이름에 uuid를 부착하기
		// 3. UUID 값이 부착된 파일이름은 서버에 업로드가 될 것이고..
		// 만약 해커가 해당 파일이름을 알게되어 동일한 이름의 파일을 만들어서 업로드 하면, 다시 새로운 uuid가 부착되어
		// 원래 저장된 파일을 보호한다.

		String strUUID = UUID.randomUUID().toString();
		String saveFileName = strUUID + originalFileName;

		// 서버의 저장폴더 +파일이름을 합성하여 파일저장 준비를 함

		File saveFile = new File(rootFolder, saveFileName);
		try {
			file.transferTo(saveFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// UUID가 부착된 파일이름을 controller로 return하여 DB에 저장하는 용도로 사용한다.

		return saveFileName;

	}

	// 파일이름을 받아서 파일을 삭제 
	
	@Override
	public boolean fileDelete(String b_file) {
		
		boolean ret = false;
		File deleteFile = new File(rootFolder, b_file);
		if(deleteFile.exists()) {
			
			//파일을 삭제하면 true return
			ret = deleteFile.delete();
	
		}
		
		return ret;
	}

}












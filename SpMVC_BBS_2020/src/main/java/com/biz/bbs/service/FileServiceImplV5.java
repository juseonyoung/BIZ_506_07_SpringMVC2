package com.biz.bbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.bbs.model.ImageVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("fileServiceV5")

// 멀티파일을 매개변수로 받아서 리스트로 각 파일을 추출한 후
// fileup method를 호출하여 파일들을 upload하고
// 파일이름을 다시 list로 만들고 return
public class FileServiceImplV5 extends FileServiceImplV4 {

	@Override
	public List<ImageVO> fileUp(MultipartHttpServletRequest files) {
		// TODO Auto-generated method stub

		List<MultipartFile> fileList = files.getFiles("files");
		List<ImageVO> fileNames = new ArrayList<ImageVO>();
		

		for (MultipartFile f : fileList) {

			if (!f.getOriginalFilename().isEmpty()) {
				String fileName = this.fileUp(f); // 업로드된파일
				ImageVO imageVO = ImageVO.builder().i_org_name(f.getOriginalFilename()).i_file_name(fileName).build(); //업로드 후 바ㅓ뀐 이름을
				//담음
				
				
				fileNames.add(imageVO);
			}

		}

		return fileNames;
	}

}

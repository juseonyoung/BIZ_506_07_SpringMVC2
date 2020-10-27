package com.biz.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.ems.mapper.EmsDao;
import com.biz.ems.mapper.ImageDao;
import com.biz.ems.model.EmsVO;
import com.biz.ems.model.ImageVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("emsServiceV1")
public class EmsServiceImplV1 implements EmsService{

	@Autowired
	protected EmsDao emsDao;
	
	@Autowired
	protected ImageDao imageDao;
	
	@Autowired
	@Qualifier("fileServiceV5")
	protected FileService fileService;

	
	

	
	@Override
	public List<EmsVO> selectAll() {
		// TODO Auto-generated method stub
		return emsDao.selectAll();
	}

	@Override
	public void insert(EmsVO emsVO, MultipartFile file) {
		// TODO Auto-generated method stub
		
		log.debug(emsVO.toString());
		String fileName = fileService.fileUp(file);
		emsVO.setS_file1(fileName);
		emsVO.setS_file2(fileName);
		
		emsDao.insert(emsVO);
		
	}

	

	@Override
	public EmsVO findBySeq(long long_seq) {
		
		EmsVO emsVO = emsDao.findBySeq(long_seq);
		List<ImageVO> images = imageDao.fineBySeq(long_seq);
		
		emsVO.setImages(images);
		return emsVO;
	}

	@Override
	public int delete(long long_seq) {
		// TODO Auto-generated method stub
		
		EmsVO emsVO = emsDao.findBySeq(long_seq);
		
		String s_file1 = emsVO.getS_file1();
		String s_file2 = emsVO.getS_file2();
		
		if(s_file1 !=null) {
			fileService.fileDelete(s_file1, s_file2);
		}
		
		return emsDao.delete(long_seq);
		
	}
	
	
	@Override
	public void insert(EmsVO emsVO) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public List<String> insert(EmsVO emsVO, MultipartHttpServletRequest files) {
		// TODO Auto-generated method stub
		
		
		List<MultipartFile> fileList = files.getFiles("files");
		
		for(MultipartFile f : fileList) {
			log.debug("업로드 파일 {}", f.getOriginalFilename());
		}
		
		List<ImageVO> fileNames = fileService.fileUp(files);
		
		emsDao.insert(emsVO);
		long b_seq = emsVO.getId();
		log.debug("EMS SEQ {}", b_seq);
		
		for(ImageVO vo : fileNames) {
			imageDao.insert(vo, b_seq);
		}
		
		return null;
		
	
	}

	
}

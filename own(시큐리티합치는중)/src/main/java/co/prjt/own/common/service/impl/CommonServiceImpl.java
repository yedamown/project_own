package co.prjt.own.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.prjt.own.common.mapper.CommonMapper;
import co.prjt.own.common.service.CommonService;
import co.prjt.own.common.service.ExersubVO;
import co.prjt.own.common.service.MultimediaVO;
import co.prjt.own.common.web.FileDto;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	CommonMapper commonMapper;
	
	@Value("${spring.servlet.multipart.location}")
	String filePath;
	
	@Override
	public List<ExersubVO> getListExersub() {
		// TODO Auto-generated method stub
		return commonMapper.getListExersub();
	}

	@Override
	public List<MultimediaVO> fileUpload() {
		// TODO Auto-generated method stub
		return commonMapper.fileUpload();
	}

	@Override
	public String upload(MultipartFile[] uploadfile, String IndenfityId,  String INO ,String category){
		List<FileDto> list = new ArrayList<>();
		
		for(MultipartFile file : uploadfile) {
			MultimediaVO vo = new MultimediaVO();
			
			vo.setMediaFilePath(filePath);
			
			
			vo.setIdentifyId(IndenfityId);
			vo.setMediaCategory(category);
			
			if(!file.isEmpty()) {
				FileDto dto = new FileDto(UUID.randomUUID().toString(),
											file.getOriginalFilename(),
											file.getContentType());
				list.add(dto);
				File newFileName = new File(dto.getUuid()+"_"+dto.getFileName());
			vo.setMediaRealFile(dto.getFileName());	
			vo.setMediaServerFile(dto.getUuid()+"_"+dto.getFileName());
				try {
					file.transferTo(newFileName);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			vo.setIno(INO);
			commonMapper.upload(vo);
			}
		
		return null;
	}

	//멀티미디어에서 각 식별번호로 멀티미디어 파일 검색
	@Override
	public List<MultimediaVO> selectImgAll(String no) {
		return commonMapper.selectImgAll(no);
	}


}

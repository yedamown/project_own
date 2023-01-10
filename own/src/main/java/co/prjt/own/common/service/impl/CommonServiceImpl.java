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
	public String upload(MultipartFile[] uploadfile, String IndenfityId, String category){
		List<FileDto> list = new ArrayList<>();
		for(MultipartFile file : uploadfile) {
			if(!file.isEmpty()) {
				FileDto dto = new FileDto(UUID.randomUUID().toString(),
											file.getOriginalFilename(),
											file.getContentType());
				list.add(dto);
				File newFileName = new File(dto.getUuid()+"_"+dto.getFileName());
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
			}
		
			
		for(int i=0; i<list.size(); i++) {
	         MultimediaVO vo= new MultimediaVO();
	         vo.setMediaRealFile(list.get(i).getFileName());
	         vo.setMediaServerFile(list.get(i).getUuid()+"_"+list.get(i).getFileName());
	         vo.setMediaFilePath(filePath); //test용, 게시글 번호
	         // commonService.인서트
		}
		return null;
	}

}

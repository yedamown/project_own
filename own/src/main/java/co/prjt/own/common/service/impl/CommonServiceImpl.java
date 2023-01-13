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
			
			//멀티미디어 이름 넣어주기
			if(!file.isEmpty()) {
				FileDto dto = new FileDto(UUID.randomUUID().toString(),
											file.getOriginalFilename(),
											file.getContentType());
				list.add(dto);
				File newFileName = new File(dto.getUuid()+"_"+dto.getFileName());
			//리얼이름 설정
			vo.setMediaRealFile(dto.getFileName());	
			//서버이름 설정
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
			//식별번호 알파벳 붙이기
			vo.setIno(INO);
			//매퍼에서 db로 정보 insert
			commonMapper.uploadImg(vo);
			}
		
		return null;
	}

	//DB에 넣기
	@Override
	public void uploadImg(MultimediaVO vo) {
		commonMapper.uploadImg(vo);
	}

	//멀티미디어 사진 업데이트
	@Override
	public String update(MultipartFile[] uploadfile, MultimediaVO vo) {
		//식별번호로 가져온 vo 확인하기
		System.out.println(vo);
		List<FileDto> list = new ArrayList<>();
		for(MultipartFile file : uploadfile) {
			//저장위치 넣기
			vo.setMediaFilePath(filePath);
			//멀티미디어 이름 넣어주기
			if(!file.isEmpty()) {
				FileDto dto = new FileDto(UUID.randomUUID().toString(),
											file.getOriginalFilename(),
											file.getContentType());
				list.add(dto);
			//저장되는 이름 설정? 인듯? 잘몰겠음 ㅠ
				File newFileName = new File(dto.getUuid()+"_"+dto.getFileName());
			//리얼이름 설정 새로설정해야함 업데이트니깐.
			vo.setMediaRealFile(dto.getFileName());	
			//서버이름 설정 이것도 새로설정
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
			//매퍼에서 데이터를 업데이트하는
			commonMapper.updateImg(vo);
			}
		
		return null;
	}
	
	//DB내용 업데이트 수정
	@Override
	public void updateImg(MultimediaVO vo) {
		commonMapper.updateImg(vo);
	}

	//멀티미디어에서 각 식별번호로 멀티미디어 파일 검색 (여러개)
	@Override
	public List<MultimediaVO> selectImgAll(String no) {
		return commonMapper.selectImgAll(no);
	}

	//멀티미디어에서 식별번호로 단건조회
	@Override
	public MultimediaVO selectImg(String no) {
		return commonMapper.selectImg(no);
	}
}

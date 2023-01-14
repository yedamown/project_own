package co.prjt.own.common.mapper;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.prjt.own.common.service.ExersubVO;
import co.prjt.own.common.service.MultimediaVO;

public interface CommonMapper {
	//운동분류를 가져오는것.
	public List<ExersubVO> getListExersub();
	
	//이미지넣기
	public List<MultimediaVO> fileUpload();
	
	// 멀티미디어 파일변환하고, 이름값? 가져오는..?
	public String upload(MultipartFile[] uploadfile,String IndenfityId, String INO, String category);
	//추가하라고 빨간줄뜨길래 추가한것 mapper에서 insert해주는
	public void uploadImg(MultimediaVO vo);
	
	//멀티미디어 사진 수정하기 (식별번호로 검색) -> 파일로변환해서 넣기?
	public String update(MultipartFile[] uploadfile, MultimediaVO vo);
	//테이블에 정보입력
	public void updateImg(MultimediaVO vo);
	
	//멀티미디어  검색하기 (여러개)
	public List<MultimediaVO> selectImgAll(String value);
	
	//멀티미디어 단건조회
	public MultimediaVO selectImg(String value);

}

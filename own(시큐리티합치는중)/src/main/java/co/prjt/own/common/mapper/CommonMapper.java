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
	
	// 멀티미디어 테이블에 넣기
	public String upload(MultipartFile[] uploadfile,String IndenfityId, String INO, String category);
	
	//멀티미디어 검색하기
	public List<MultimediaVO> selectImgAll(String no);

	public void upload(MultimediaVO vo);
}

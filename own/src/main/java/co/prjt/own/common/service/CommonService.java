package co.prjt.own.common.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
	//운동분류를 가져오는것.
	public List<ExersubVO> getListExersub();
	//이미지넣기
	public List<MultimediaVO> fileUpload();
	// 멀티미디어 테이블에 넣기
	public String upload(MultipartFile[] uploadfile,String IndenfityId, String INO, String category);

}

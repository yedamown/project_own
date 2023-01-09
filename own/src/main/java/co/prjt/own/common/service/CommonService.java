package co.prjt.own.common.service;

import java.util.List;

public interface CommonService {
	//운동분류를 가져오는것.
	public List<ExersubVO> getListExersub();
	//이미지넣기
	public List<MultimediaVO> fileUpload();
}

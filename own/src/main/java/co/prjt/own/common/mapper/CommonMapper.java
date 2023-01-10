package co.prjt.own.common.mapper;

import java.util.List;

import co.prjt.own.common.service.ExersubVO;
import co.prjt.own.common.service.MultimediaVO;

public interface CommonMapper {
	// 운동목록 가져오기
	public List<ExersubVO> getListExersub();
	//이미지넣기
	public List<MultimediaVO> fileUpload();
}

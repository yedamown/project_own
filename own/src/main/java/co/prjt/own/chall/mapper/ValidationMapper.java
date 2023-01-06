package co.prjt.own.chall.mapper;

import java.util.List;

import co.prjt.own.chall.service.ValidationVO;

public interface ValidationMapper {
	//인증등록
	int insertVld(ValidationVO vo);
	
	//인증상태변경
	int updateVld(ValidationVO vo);
	
	//인증조회
	ValidationVO getVld(ValidationVO vo);
	
	//전체조회
	List<ValidationVO> getVldAll(ValidationVO vo);
}

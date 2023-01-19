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
	
	//전체조회 (도전번호로 조회)
	List<ValidationVO> getChallVld(ValidationVO vo);
	
	//내 인증리스트(아이디로 조회)
	List<ValidationVO> getMyVld(ValidationVO vo);
	
	//내 도전조회(도전번호 & 아이디)
	List<ValidationVO> getMyChallVld(ValidationVO vo);
	
	//각 도전 한주 인증횟수 카운트 
	int countWeekVld(ValidationVO vo);
		
	//각 도전별 총 인증횟수 카운트
	int countVld(ValidationVO vo);
	
	int startToToday(ValidationVO vo);
	
}

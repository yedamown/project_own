package co.prjt.own.chall.mapper;

import java.util.List;

import co.prjt.own.chall.service.CMemberListVO;

public interface CMemberListMapper {
	//도전신청(회원 도전신청 시 입력)
	int insertMemList(CMemberListVO vo);

	//도전 승인 상태변경 (수정)
	int updateMemList(CMemberListVO vo);
	
	//멤버상태 하나 찾기
	CMemberListVO getMemCheck(CMemberListVO vo);
		
	//전체리스트
	List<CMemberListVO> getMemListAll(CMemberListVO vo);
	
}

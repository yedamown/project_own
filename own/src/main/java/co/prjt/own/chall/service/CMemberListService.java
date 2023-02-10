package co.prjt.own.chall.service;

import java.util.List;

public interface CMemberListService {
	//도전신청(회원 도전신청 시 입력)
	int insertMemList(CMemberListVO vo);

	//도전 승인 상태변경 (수정)
	int updateMemList(CMemberListVO vo);
	
	//멤버상태 하나 찾기
	CMemberListVO getMemCheck(CMemberListVO vo);
		
	//신청한적 있는지 검색
	CMemberListVO applyCheck(CMemberListVO vo);
	
	//전체리스트
	List<CMemberListVO> getMemListAll(CMemberListVO vo);
	
	//도전에 참여중인 사람 수
	int getChallMemNum(String challNo);
}

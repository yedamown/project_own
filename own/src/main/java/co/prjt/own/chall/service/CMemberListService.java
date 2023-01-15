package co.prjt.own.chall.service;

import java.util.List;

public interface CMemberListService {
	//도전신청(회원 도전신청 시 입력)
	int insertMemList(CMemberListVO vo);

	//도전끝나고 상태변경 (수정)
	CMemberListVO updateMemList(CMemberListVO vo);

	//멤버참여 여부 체크
	CMemberListVO getMemCheck(CMemberListVO vo);
	
	//동적쿼리로 도전
	List<CMemberListVO> getMemListAll(CMemberListVO vo);
}

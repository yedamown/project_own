package co.prjt.own.chall.service;

import java.util.List;

public interface CMemberListService {
	//도전신청(회원 도전신청 시 입력)
	CMemberListVO insertMemList(CMemberListVO vo);

	//도전끝나고 상태변경 (수정)
	CMemberListVO updateMemList(CMemberListVO vo);
	
	//내 도전(회원아이디로)/도전별회원 보기(도전식별번호로 조회)
	//  	참여중회원(도전식별번호, 상태로 조회)
	List<CMemberListVO> getMemList(CMemberListVO vo);
	
	//전체리스트
	List<CMemberListVO> getMemListAll(CMemberListVO vo);
}

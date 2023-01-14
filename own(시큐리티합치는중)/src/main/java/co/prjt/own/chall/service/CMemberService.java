package co.prjt.own.chall.service;

import java.util.List;

public interface CMemberService {
	//도전회원 등록
	int insertCMem(CMemberVO vo); //닉네임과 소개만 받아서 자동가입
	int updateCMem(CMemberVO vo); //닉네임, 소개만 업데이트
	 
	//도전회원 정보조회
	CMemberVO getCMem(CMemberVO vo);
	
	//도전회원정보 전체조회
	List<CMemberVO> getCMemAll(CMemberVO vo);
}

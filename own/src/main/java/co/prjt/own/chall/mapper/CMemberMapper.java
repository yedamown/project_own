package co.prjt.own.chall.mapper;

import java.util.List;

import co.prjt.own.chall.service.CMemberVO;

public interface CMemberMapper {
	//도전회원 등록
	int insertCMem(CMemberVO vo); //닉네임과 소개만 받아서 자동가입
	int updateCMem(CMemberVO vo); //닉네임, 소개만 업데이트
	
	//아이디 중복체크
	int nickCheck(String nickName);
	
	//도전회원 정보조회 -> 미디어랑 조인해서 대표이미지
	CMemberVO getCMem(CMemberVO vo);
	
	//단순 멤버조회
	CMemberVO challMemCheck(CMemberVO vo);
	
	//도전회원정보 전체조회
	List<CMemberVO> getCMemAll(CMemberVO vo);
}

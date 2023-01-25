package co.prjt.own.chall.service;

import java.util.List;
import java.util.Map;

import co.prjt.own.common.Paging;

//도전
public interface ChallengeService {
	//등록, 수정, 삭제
	int insertChall(ChallengeVO vo);
	int updateChall(ChallengeVO vo);
	int deleteChall(ChallengeVO vo);
	
	//도전단건 조회
	ChallengeVO getChall(ChallengeVO vo);
	
	
	/* -----------------------------모든 도전 리스트 페이징 ------------------------------*/
	
	//조건에 따라 전체 리스트 조회
	List<ChallengeVO> getChallAll(ChallengeVO vo);
	
	//도전에 관한 조건으로만 도전 수 카운트 - 우선은 진행 중인 도전만 검색
	int countChall (ChallengeVO vo);
	
	//페이징 리스트 3개씩 보여주기!
	List<ChallengeVO> pageChallList(ChallengeVO vo, Paging paging);  
	
	
	/* -----------------------------내 도전 리스트 페이징 ------------------------------*/
	
	//나의 도전 카운트 - 멤버리스트와 조인해야함.
	int countMychall(String id);
	
	//멤버리스트와 조인 - 아이디로 검색 (챌린지vo에 아이디 부분 넣어버림..)
	List<ChallengeVO> getMyChall(ChallengeVO vo);
	
	//마이페이지 - 나의도전목록 페이징 6개씩
	List<ChallengeVO> myPageChall(ChallengeVO vo ,Paging paging);  
	
	/* ------------------------------  도전 검색 ------------------------------------------*/
	
	List<ChallengeVO> searchChall(String words);
	
}

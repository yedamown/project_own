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
	
	//멤버리스트와 조인 - 아이디로 검색
	List<ChallengeVO> getMyChall(String userId);
	
	//조건에 따라 전체 리스트 조회
	List<ChallengeVO> getChallAll(ChallengeVO vo);
	
	//내 도전 수 카운트 - 우선은 진행 중인 도전만 검색
	int countChall (ChallengeVO vo);
	
	//페이징 리스트 3개씩 보여주기!
	List<ChallengeVO> pageChallList(ChallengeVO vo, Paging paging);  
}

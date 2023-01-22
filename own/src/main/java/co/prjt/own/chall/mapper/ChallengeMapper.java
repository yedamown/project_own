package co.prjt.own.chall.mapper;

import java.util.List;
import java.util.Map;

import co.prjt.own.chall.service.CMemberListVO;
import co.prjt.own.chall.service.ChallengeVO;
import co.prjt.own.common.Paging;

public interface ChallengeMapper {
	//등록, 수정, 삭제
	int insertChall(ChallengeVO vo);
	int updateChall(ChallengeVO vo);
	int deleteChall(ChallengeVO vo);
	
	//도전단건 조회
	ChallengeVO getChall(ChallengeVO vo);
		
	
	
	/* -----------------------------모든 도전 리스트 페이징 ------------------------------*/
	
	//조건에 따라 전체 리스트 조회
	List<ChallengeVO> getChallAll(ChallengeVO vo);
	
	//내 도전 수 카운트 - 우선은 진행 중인 도전만 검색
	int countChall (ChallengeVO vo);
	
	//페이징 리스트 3개씩 보여주기!
	List<ChallengeVO> pageChallList(ChallengeVO vo, Paging paging);  
	
	
	/* -----------------------------내 도전 리스트 페이징 ------------------------------*/
	
	//멤버리스트와 조인 - 아이디로 검색
	List<ChallengeVO> getMyChall(String userId);
	
	//마이페이지 - 나의도전목록 페이징 6개씩
	List<ChallengeVO> myPageChall(CMemberListVO mlvo, ChallengeVO vo ,Paging paging);  
}

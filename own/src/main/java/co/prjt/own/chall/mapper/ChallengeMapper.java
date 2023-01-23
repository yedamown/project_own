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
	
	//도전에 관한 조건으로만 도전 수 카운트 - 우선은 진행 중인 도전만 검색
	int countChall (ChallengeVO vo);	
	
	//조건에 따라 전체 리스트 조회
	List<ChallengeVO> getChallAll(ChallengeVO vo);

	/* -----------------------------내 도전 리스트 페이징 ------------------------------*/
	
	//나의 도전 카운트 - 멤버리스트와 조인해야함.
	int countMychall(String id);
	
	//멤버리스트와 조인 - 아이디로 검색
	List<ChallengeVO> getMyChall(ChallengeVO vo);
 
}

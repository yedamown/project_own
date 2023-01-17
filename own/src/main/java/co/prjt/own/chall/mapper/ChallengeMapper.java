package co.prjt.own.chall.mapper;

import java.util.List;
import java.util.Map;

import co.prjt.own.chall.service.ChallengeVO;

public interface ChallengeMapper {
	//등록, 수정, 삭제
	int insertChall(ChallengeVO vo);
	int updateChall(ChallengeVO vo);
	int deleteChall(ChallengeVO vo);
	
	//도전단건 조회
	ChallengeVO getChall(ChallengeVO vo);
	
	//조건에 따라 도전 리스트 조회
	List<ChallengeVO> getChallAll(ChallengeVO vo);
	
	//멤버리스트와 조인 - 아이디로 검색
	List<ChallengeVO> getMyChall(String userId);
	
	//멀티미디어와 조인 - 아이디로 검색
	//멀티미디어와 조인 - 챌린지 카테고리로 검색
	//멀티미디어 조인 - 태그로 검색
	//멀티미디어
	
}

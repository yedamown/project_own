package co.prjt.own.chall.service;

import java.util.List;
import java.util.Map;

//도전
public interface ChallengeService {
	//등록, 수정, 삭제
	int insertChall(ChallengeVO vo);
	int updateChall(ChallengeVO vo);
	int deleteChall(ChallengeVO vo);
	
	//도전단건 조회
	ChallengeVO getChall(ChallengeVO vo);
	
	//도전 리스트 조회
	List<ChallengeVO> getChallAll(ChallengeVO vo);
}

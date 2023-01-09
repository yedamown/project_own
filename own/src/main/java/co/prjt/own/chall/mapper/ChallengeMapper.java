package co.prjt.own.chall.mapper;

import java.util.List;
import java.util.Map;

import co.prjt.own.chall.service.ChallengeVO;

public interface ChallengeMapper {
	//도전 Challenge
	int insertChall(ChallengeVO vo);
	int updateChall(ChallengeVO vo);
	int deleteChall(ChallengeVO vo);
	ChallengeVO getChall(ChallengeVO vo);
	List<ChallengeVO> getChallAll(ChallengeVO vo);
}

package co.prjt.own.chall.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.prjt.own.chall.mapper.ChallengeMapper;
import co.prjt.own.chall.service.ChallengeService;
import co.prjt.own.chall.service.ChallengeVO;

@Component
public class ChallengeServiceImpl implements ChallengeService{

	@Autowired ChallengeMapper mapper;

	@Override
	public int insertChall(ChallengeVO vo) {
		return mapper.insertChall(vo);
	}

	@Override
	public int updateChall(ChallengeVO vo) {
		return mapper.updateChall(vo);
	}

	@Override
	public int deleteChall(ChallengeVO vo) {
		return mapper.deleteChall(vo);
	}

	@Override
	public ChallengeVO getChall(ChallengeVO vo) {
		return mapper.getChall(vo);
	}

	@Override
	public List<ChallengeVO> getChallAll(ChallengeVO vo) {
		// TODO Auto-generated method stub
		return mapper.getChallAll(null);
	}


}

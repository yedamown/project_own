package co.prjt.own.chall.service.impl;

import java.util.List;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateChall(ChallengeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteChall(ChallengeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ChallengeVO getChall(ChallengeVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChallengeVO> getChallAll(ChallengeVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

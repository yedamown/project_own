package co.prjt.own.chall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.chall.mapper.CResultMapper;
import co.prjt.own.chall.service.CResultService;
import co.prjt.own.chall.service.CResultVO;

@Service
public class CResultServiceImpl implements CResultService{

	@Autowired CResultMapper mapper;

	@Override
	public int insertCResult(CResultVO vo) {
		return mapper.insertCResult(vo);
	}

	@Override
	public int updateResult(CResultVO vo) {
		return mapper.updateResult(vo);
	}

	@Override
	public int updateReward(CResultVO vo) {
		return mapper.updateReward(vo);
	}

//	결과 단건조회
	@Override
	public CResultVO getResult(CResultVO vo) {
		return mapper.getResult(vo);
	}

	//결과 성공률 평균구하기
	@Override
	public double avgSuccessRate(CResultVO vo) {
		return mapper.avgSuccessRate(vo);
	}

}

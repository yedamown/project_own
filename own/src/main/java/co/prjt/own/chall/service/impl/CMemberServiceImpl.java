package co.prjt.own.chall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.prjt.own.chall.mapper.CMemberMapper;
import co.prjt.own.chall.mapper.ChallengeMapper;
import co.prjt.own.chall.service.CMemberService;
import co.prjt.own.chall.service.CMemberVO;

@Component
public class CMemberServiceImpl implements CMemberService{

	@Autowired CMemberMapper mapper;
	
	@Override
	public int insertCMem(CMemberVO vo) {
		return mapper.insertCMem(vo);
	}

	@Override
	public int updateCMem(CMemberVO vo) {
		return mapper.updateCMem(vo);
	}

	@Override
	public CMemberVO getCMem(CMemberVO vo) {
		return mapper.getCMem(vo);
	}

	@Override
	public List<CMemberVO> getCMemAll(CMemberVO vo) {
		return mapper.getCMemAll(vo);
	}

	//아이디 중복체크
	@Override
	public int nickCheck(String nickName) {
		return mapper.nickCheck(nickName);
	}
	
	//미디어랑 조인안한 단순 조회.
	@Override
	public CMemberVO challMemCheck(CMemberVO vo) {
		return mapper.challMemCheck(vo);
	}

}

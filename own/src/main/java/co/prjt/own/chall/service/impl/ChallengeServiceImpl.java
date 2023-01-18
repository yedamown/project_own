package co.prjt.own.chall.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.prjt.own.chall.mapper.ChallengeMapper;
import co.prjt.own.chall.service.ChallengeService;
import co.prjt.own.chall.service.ChallengeVO;
import co.prjt.own.common.Paging;

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
	public List<ChallengeVO> getMyChall(String userId) {
		return mapper.getMyChall(userId);
	}

	@Override
	public List<ChallengeVO> getChallAll(ChallengeVO vo) {
		return mapper.getChallAll(vo);
	}
	
	@Override
	//도전 수 카운트
	public int countChall(ChallengeVO vo) {
		return mapper.countChall(vo);
	}

	@Override
	//페이징 3개씩..
	public List<ChallengeVO> pageChallList(ChallengeVO vo, Paging paging) {
		paging.setTotalRecord(mapper.countChall(vo));
		System.out.println(mapper.countChall(vo));
		paging.setPageUnit(3); //한페이지에 몇개
		paging.setPageSize(4); //페이징 ? 갯수 몇번까지?
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		vo.setPaging(paging);
		System.out.println(vo);
		List<ChallengeVO> list = mapper.getChallAll(vo);
		return list;
	}

}

package co.prjt.own.chall.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.prjt.own.chall.mapper.ChallengeMapper;
import co.prjt.own.chall.service.CMemberListVO;
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
	//도전 단건검색
	public ChallengeVO getChall(ChallengeVO vo) {
		return mapper.getChall(vo);
	}

	@Override
	//내가 참여중인 도전 검색 - 페이징 갯수도 추가해서
	public List<ChallengeVO> getMyChall(ChallengeVO vo) {
		return mapper.getMyChall(vo);
	}

	@Override
	//전체 도전검색 - 페이징 갯수도 추가해서
	public List<ChallengeVO> getChallAll(ChallengeVO vo) {
		return mapper.getChallAll(vo);
	}
	
	@Override
	//도전 수 카운트
	public int countChall(ChallengeVO vo) {
		return mapper.countChall(vo);
	}
	
	@Override
	//나의 도전 수 카운트
	public int countMychall(String id) {
		return mapper.countMychall(id);
	}

	@Override
	//페이징 3개씩..
	public List<ChallengeVO> pageChallList(ChallengeVO vo, Paging paging) {
		//밖에서 하고오면 보는갯수 줄어들지않을까?
		paging.setTotalRecord(mapper.countChall(vo));
		System.out.println(mapper.countChall(vo));
		paging.setPageUnit(3); //한페이지에 몇개
		paging.setPageSize(4); //페이징 ? 갯수 몇번까지?
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		vo.setPaging(paging);
		System.out.println(vo);
		List<ChallengeVO> list = mapper.getChallAll(vo);
		list.get(0).setPaging(paging);
		return list;
	}

	@Override
	//내 도전 페이징 6개씩
	public List<ChallengeVO> myPageChall(ChallengeVO vo, Paging paging) {
		//검색할 아이디 잘 왔는지 체크
		System.out.println(vo.getUserId());
		//내 도전갯수 세기
		paging.setTotalRecord(mapper.countMychall(vo.getUserId()));
		//페이징 정보 우선 검색할 거에 담아두기.
//		밖에서 정하고가져고온다..
//		paging.setPageUnit(6); //내 도전이라 6개씩 보여줄 것
//		paging.setPageSize(4); //페이징 동그라미로 할 거라 4개
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		vo.setPaging(paging);
		System.out.println(vo);
		List<ChallengeVO> list = mapper.getMyChall(vo);
		list.get(0).setPaging(paging);
		return list;
	}

	@Override
	public List<ChallengeVO> searchChall(String words) {
		return mapper.searchChall(words);
	}


}

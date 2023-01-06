package co.prjt.own.chall.mapper;

import java.util.List;

import co.prjt.own.chall.service.CMemberVO;

public interface CMemberMapper {
	//도전회원 CMember
	int insertCMem(CMemberVO vo); 
	int updateCMem(CMemberVO vo); 
	CMemberVO getCMem(CMemberVO vo);
	List<CMemberVO> getCMemAll(CMemberVO vo);
}

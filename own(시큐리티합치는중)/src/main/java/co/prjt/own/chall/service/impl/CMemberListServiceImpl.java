package co.prjt.own.chall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.prjt.own.chall.mapper.CMemberListMapper;
import co.prjt.own.chall.service.CMemberListService;
import co.prjt.own.chall.service.CMemberListVO;

@Component
public class CMemberListServiceImpl implements CMemberListService{

	@Autowired CMemberListMapper mapper;
	
	@Override
	public int insertMemList(CMemberListVO vo) {
		return mapper.insertMemList(vo);
	}

	@Override
	public CMemberListVO updateMemList(CMemberListVO vo) {
		return mapper.updateMemList(vo);
	}
	
	@Override
	public List<CMemberListVO> getMemList(CMemberListVO vo) {
		return mapper.getMemList(vo);
	}

	@Override
	public List<CMemberListVO> getMemListAll(CMemberListVO vo) {
		return mapper.getMemListAll(vo);
	}


}

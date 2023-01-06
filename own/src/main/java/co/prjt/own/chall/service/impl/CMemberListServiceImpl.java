package co.prjt.own.chall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.prjt.own.chall.mapper.CMemberListMapper;
import co.prjt.own.chall.service.CMemberListService;
import co.prjt.own.chall.service.CMemberListVO;

public class CMemberListServiceImpl implements CMemberListService{

	@Autowired CMemberListMapper mapper;
	
	@Override
	public CMemberListVO insertMemList(CMemberListVO vo) {
		return null;
	}

	@Override
	public CMemberListVO updateMemList(CMemberListVO vo) {
		return null;
	}
	
	@Override
	public List<CMemberListVO> getMemList(CMemberListVO vo) {
		return null;
	}

	@Override
	public List<CMemberListVO> getMemListAll(CMemberListVO vo) {
		return null;
	}


}

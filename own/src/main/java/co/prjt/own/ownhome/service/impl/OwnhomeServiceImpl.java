package co.prjt.own.ownhome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.prjt.own.ownhome.mapper.OwnhomeMapper;
import co.prjt.own.ownhome.service.EmpVO;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;

public class OwnhomeServiceImpl implements OwnhomeService {

	@Autowired
	OwnhomeMapper ownhomeMapper;
	
	@Override
	public int insertUser(OwnUserVO UserVO) {
		// TODO Auto-generated method stub
		return ownhomeMapper.insertUser(UserVO);
	}

	@Override
	public int login(String id) {
		// TODO Auto-generated method stub
		return ownhomeMapper.login(id);
	}

	@Override
	public List<OwnUserVO> getUserList(OwnUserVO vo) {
		// TODO Auto-generated method stub
		return ownhomeMapper.getUserList(vo);
	}
	
}

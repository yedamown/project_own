package co.prjt.own.ownhome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.ownhome.mapper.OwnhomeMapper;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;

@Service
public class OwnhomeServiceImpl implements OwnhomeService {

	@Autowired
	OwnhomeMapper ownhomeMapper;
	
	@Override
	public int insertUser(OwnUserVO UserVO) {
		return ownhomeMapper.insertUser(UserVO);
	}

	@Override
	public OwnUserVO login(String id) {
		return ownhomeMapper.login(id);
	}

	@Override
	public List<OwnUserVO> getUserList(OwnUserVO vo) {
		return ownhomeMapper.getUserList(vo);
	}

	@Override
	public String snsLogin(String id) {
		return ownhomeMapper.snsLogin(id);
	}

	@Override
	public int updateSnsUser(String id) {
		return ownhomeMapper.updateSnsUser(id);
	}
	
}

package co.prjt.own.ownhome.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.ownhome.mapper.OwnhomeMapper;
import co.prjt.own.ownhome.service.OwnUserVO;
import co.prjt.own.ownhome.service.OwnhomeService;
import co.prjt.own.sns.service.SAccountVO;

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
	public SAccountVO snsLogin(String id) {
		return ownhomeMapper.snsLogin(id);
	}

	@Override
	public int updateSnsUser(@Param ("snsNickname") String nickname, @Param ("userId") String id) {
		return ownhomeMapper.updateSnsUser(nickname, id);
	}

	@Override
	public int idcheck(String id) {
		// TODO Auto-generated method stub
		return ownhomeMapper.idcheck(id);
	}

	@Override
	public void searchId(String email) {
		// TODO Auto-generated method stub
		
	}
	
}

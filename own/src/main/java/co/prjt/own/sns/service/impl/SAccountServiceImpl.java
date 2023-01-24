package co.prjt.own.sns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.sns.mapper.SAccountMapper;
import co.prjt.own.sns.service.SAccountService;
import co.prjt.own.sns.service.SAccountVO;

@Service
public class SAccountServiceImpl implements SAccountService {

	@Autowired SAccountMapper sAccountMapper;
	
	@Override
	public List<SAccountVO> getSnsUserList(SAccountVO SAccountVO) {
		return sAccountMapper.getSnsUserList(SAccountVO);
	}

	@Override
	public SAccountVO getSnsUser(String snsNickname) {
		return sAccountMapper.getSnsUser(snsNickname);
	}

	@Override
	public int insertSnsUser(SAccountVO vo) {
		return sAccountMapper.insertSnsUser(vo);
	}

	@Override
	public int updateSnsUser(SAccountVO SAccountVO) {
		return sAccountMapper.updateSnsUser(SAccountVO);
	}

	@Override
	public int deleteSnsUser(String snsNickname) {
		return sAccountMapper.deleteSnsUser(snsNickname);
	}

	@Override
	public int snsImgCount(String id) {
		return sAccountMapper.snsImgCount(id);
	}

}

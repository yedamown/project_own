package co.prjt.own.sns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.sns.mapper.SAccountMapper;
import co.prjt.own.sns.service.SAccountService;
import co.prjt.own.sns.service.SAccountVO;

@Service
public class SAccountServiceImpl implements SAccountService {

	@Autowired SAccountMapper sAccountMppaer;
	
	@Override
	public List<SAccountVO> getSnsUserList(SAccountVO SAccountVO) {
		return sAccountMppaer.getSnsUserList(SAccountVO);
	}

	@Override
	public SAccountVO getSnsUser(String snsNickname) {
		return sAccountMppaer.getSnsUser(snsNickname);
	}

	@Override
	public int insertSnsUser(SAccountVO SAccountVO) {
		return sAccountMppaer.insertSnsUser(SAccountVO);
	}

	@Override
	public int updateSnsUser(SAccountVO SAccountVO) {
		return sAccountMppaer.updateSnsUser(SAccountVO);
	}

	@Override
	public int deleteSnsUser(String snsNickname) {
		return sAccountMppaer.deleteSnsUser(snsNickname);
	}

}

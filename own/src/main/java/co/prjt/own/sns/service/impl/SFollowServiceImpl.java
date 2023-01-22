package co.prjt.own.sns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.sns.mapper.SFollowMapper;
import co.prjt.own.sns.service.SFollowService;
import co.prjt.own.sns.service.SFollowVO;

@Service
public class SFollowServiceImpl implements SFollowService {

	@Autowired SFollowMapper sFollowMapper;
	
	@Override
	public List<SFollowVO> getFollowList(String id) {
		return sFollowMapper.getFollowList(id);
	}

	@Override
	public List<SFollowVO> getFollowerList(String id) {
		return sFollowMapper.getFollowerList(id);
	}

	@Override
	public int followCount(String id) {
		return sFollowMapper.followCount(id);
	}

	@Override
	public int followerCount(String id) {
		return sFollowMapper.followerCount(id);
	}

	@Override
	public int insertFollow(String snsFollowId, String snsFollowerId) {
		return sFollowMapper.insertFollow(snsFollowId, snsFollowerId);
	}

	@Override
	public int deleteFollow(String snsFollowId, String snsFollowerId) {
		return sFollowMapper.deleteFollow(snsFollowId, snsFollowerId);
	}

	@Override
	public int isCheckFollow(String snsFollowId, String snsFollowerId) {
		return sFollowMapper.isCheckFollow(snsFollowId, snsFollowerId);
	}


}

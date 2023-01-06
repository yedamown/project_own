package co.prjt.own.sns.service;

import java.util.List;

public interface SFollowService {
	//팔로잉 조회
	public List<SFollowVO> getFollowList(String snsFollowerId);	
	
	//팔로워 조회
	public List<SFollowVO> getFollowerList(String snsFollowId);
	
	//팔로잉
	int insertFollow(SFollowVO SFollowVO);
	
	//삭제
	int deleteFollow(String snsFollowId, String snsFollowerId);
	
	
	
}

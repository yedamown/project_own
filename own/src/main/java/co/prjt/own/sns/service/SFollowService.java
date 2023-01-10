package co.prjt.own.sns.service;

import java.util.List;

public interface SFollowService {
	//팔로잉 조회
	public List<SFollowVO> getFollowList(SFollowVO vo);	
	
	//팔로워 조회
	public List<SFollowVO> getFollowerList(SFollowVO vo);
	
	//팔로잉
	int insertFollow(SFollowVO SFollowVO);
	
	//삭제
	int deleteFollow(String snsFollowId, String snsFollowerId);
	
	//팔로우 카운트
	int followCount(String id);
	
	//팔로워 카운트
	int followerCount(String id);
}

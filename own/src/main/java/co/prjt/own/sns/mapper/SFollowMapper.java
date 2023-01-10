package co.prjt.own.sns.mapper;

import java.util.List;

import co.prjt.own.sns.service.SFollowVO;

public interface SFollowMapper {
	
	//팔로잉 조회
	public List<SFollowVO> getFollowList(String snsFollowerId);	
	
	//팔로워 조회
	public List<SFollowVO> getFollowerList(String snsFollowId);
	
	//팔로잉
	int insertFollow(SFollowVO SFollowVO);
	
	//삭제
	int deleteFollow(String snsFollowId, String snsFollowerId);
	
	//팔로우 카운트
	int followCount(String id);
	
	//팔로워 카운트
	int followerCount(String id);
	
}

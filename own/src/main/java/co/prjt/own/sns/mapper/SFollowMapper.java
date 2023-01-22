package co.prjt.own.sns.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.prjt.own.sns.service.SFollowVO;

public interface SFollowMapper {
	
	//팔로잉 조회
	public List<SFollowVO> getFollowList(String id);	
	
	//팔로워 조회
	public List<SFollowVO> getFollowerList(String id);
	
	//팔로잉
	int insertFollow(@Param("snsFollowId")  String snsFollowId, 
						 @Param("snsFollowerId")String snsFollowerId);
		
	//언팔
	int deleteFollow(@Param("snsFollowId")  String snsFollowId, 
						 @Param("snsFollowerId")String snsFollowerId);
	
	//팔로우 카운트
	int followCount(String id);
	
	//팔로워 카운트
	int followerCount(String id);
	
	int isCheckFollow(@Param("snsFollowId")  String snsFollowId, 
					  @Param("snsFollowerId")String snsFollowerId);
	
}

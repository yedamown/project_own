package co.prjt.own.sns.mapper;


import java.util.List;

import co.prjt.own.sns.service.StoryVO;

public interface StoryMapper {
	
	//스토리 전체 조회
	public List<StoryVO> getStoryList(String snsNickname);
	
	//최신 스토리 조회
		public List<StoryVO> getNowStoryList(String id);
	
	//스토리 추가
	int insertStory(StoryVO SStoryVO);
	
	//스토리 삭제
	int deleteStroy(String snsStoryNo);
	
	//스토리 상태 수정
	int updateStory(StoryVO vo);
}

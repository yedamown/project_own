package co.prjt.own.sns.service;

import java.util.List;

public interface StoryService {
	//스토리 전체 조회
	public List<StoryVO> getStoryList(String snsNickname);
	
	//스토리 추가
	int insertStory(StoryVO vo);
	
	//스토리 삭제
	int deleteStory(String snsStoryNo);
	
	//스토리 상태 수정
	int updateStory(StoryVO vo);
}

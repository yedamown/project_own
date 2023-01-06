package co.prjt.own.sns.service;

import java.util.List;

public interface SStoryService {
	//스토리 전체 조회
	public List<SStoryVO> getStroyList(String snsNickname);
	
	//스토리 추가
	int insertStory(SStoryVO SStoryVO);
	
	//스토리 삭제
	int deleteStroy(String snsStoryNo);
}

package co.prjt.own.sns.mapper;

import java.util.List;

import co.prjt.own.sns.service.SStoryVO;

public interface SStoryMapper {
	
	
	//스토리 전체 조회
	public List<SStoryVO> getStroyList(String snsNickname);
	
	//스토리 추가
	int insertStory(SStoryVO SStoryVO);
	
	//스토리 삭제
	int deleteStroy(String snsStoryNo);
}

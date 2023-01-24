package co.prjt.own.sns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.sns.mapper.StoryMapper;
import co.prjt.own.sns.service.StoryService;
import co.prjt.own.sns.service.StoryVO;

@Service
public class StoryServiceImpl implements StoryService {

	@Autowired StoryMapper sStoryMapper;
	
	@Override
	public List<StoryVO> getStroyList(String snsNickname) {
		return sStoryMapper.getStroyList(snsNickname);
	}

	@Override
	public int insertStory(StoryVO SStoryVO) {
		return sStoryMapper.insertStory(SStoryVO);
	}

	@Override
	public int deleteStory(String snsStoryNo) {
		return sStoryMapper.deleteStroy(snsStoryNo);
	}

	@Override
	public int updateStory(StoryVO vo) {
		return sStoryMapper.updateStory(vo);
	}

}

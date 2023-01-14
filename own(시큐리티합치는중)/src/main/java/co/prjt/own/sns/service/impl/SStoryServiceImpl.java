package co.prjt.own.sns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.sns.mapper.SStoryMapper;
import co.prjt.own.sns.service.SStoryService;
import co.prjt.own.sns.service.SStoryVO;

@Service
public class SStoryServiceImpl implements SStoryService {

	@Autowired SStoryMapper sStoryMapper;
	
	@Override
	public List<SStoryVO> getStroyList(String snsNickname) {
		return sStoryMapper.getStroyList(snsNickname);
	}

	@Override
	public int insertStory(SStoryVO SStoryVO) {
		return sStoryMapper.insertStory(SStoryVO);
	}

	@Override
	public int deleteStroy(String snsStoryNo) {
		return sStoryMapper.deleteStroy(snsStoryNo);
	}

}

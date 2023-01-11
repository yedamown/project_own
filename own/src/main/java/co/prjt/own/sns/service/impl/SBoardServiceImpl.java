package co.prjt.own.sns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.sns.mapper.SBoardMapper;
import co.prjt.own.sns.service.SBoardService;
import co.prjt.own.sns.service.SBoardVO;

@Service
public class SBoardServiceImpl implements SBoardService {
	
	@Autowired SBoardMapper sBoardMapper;

	@Override
	public List<SBoardVO> getSnsBoardList(String id) {
		return sBoardMapper.getSnsBoardList(id);
	}

	@Override
	public SBoardVO getSnsBoard(String snsBoardNo) {
		return sBoardMapper.getSnsBoard(snsBoardNo);
	}

	@Override
	public int insertSnsBoard(String id) {
		return sBoardMapper.insertSnsBoard(id);
	}

	@Override
	public int updateSnsBoard(SBoardVO SBoardVO) {
		return sBoardMapper.updateSnsBoard(SBoardVO);
	}

	@Override
	public int deleteSnsBoard(String snsBoardNo) {
		return sBoardMapper.deleteSnsBoard(snsBoardNo);
	}

}

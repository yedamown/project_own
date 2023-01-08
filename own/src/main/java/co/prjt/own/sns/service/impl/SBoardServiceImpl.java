package co.prjt.own.sns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.prjt.own.sns.mapper.SBoardMapper;
import co.prjt.own.sns.service.SBoardService;
import co.prjt.own.sns.service.SBoardVO;

public class SBoardServiceImpl implements SBoardService {
	
	@Autowired SBoardMapper sBoardMapper;

	@Override
	public List<SBoardVO> getSnsBoardList(SBoardVO SBaordVO) {
		return sBoardMapper.getSnsBoardList(SBaordVO);
	}

	@Override
	public SBoardVO getSnsBoard(String snsBoardNo) {
		return sBoardMapper.getSnsBoard(snsBoardNo);
	}

	@Override
	public int insertSnsBoard(SBoardVO SBoardVO) {
		return sBoardMapper.insertSnsBoard(SBoardVO);
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
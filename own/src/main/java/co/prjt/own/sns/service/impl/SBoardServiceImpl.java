package co.prjt.own.sns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.common.Paging;
import co.prjt.own.common.service.ReplyVO;
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

	@Override
	public List<SBoardVO> getSnsBoardNo(String id) {
		return sBoardMapper.getSnsBoardNo(id);
	}

	@Override
	public int countBoard(String id) {
		return sBoardMapper.countBoard(id);
	}

	@Override
	public List<SBoardVO> getNowBoardList(String id) {
		return sBoardMapper.getNowBoardList(id);
	}

	@Override
	public List<ReplyVO> getBoardReplyList(String boardNo) {
		return sBoardMapper.getBoardReplyList(boardNo);
	}

	@Override
	public int insertSnsReply(ReplyVO vo) {
		return sBoardMapper.insertSnsReply(vo);
	}

	@Override
	public int updateSnsReply(ReplyVO vo) {
		return sBoardMapper.updateSnsReply(vo);
	}

	@Override
	public int deleteSnsReply(ReplyVO vo) {
		return sBoardMapper.deleteSnsReply(vo);
	}

	@Override
	public int snsReplyCount(String boardNo) {
		return sBoardMapper.snsReplyCount(boardNo);
	}

	@Override
	public List<SBoardVO> getNewBoardList() {
		return sBoardMapper.getNewBoardList();
	}

	@Override
	public List<SBoardVO> snsBoardLikeList(String id) {
		return sBoardMapper.snsBoardLikeList(id);
	}

	@Override
	public List<SBoardVO> getNowBoardListPaging(String id, Paging paging) {
		return sBoardMapper.getNowBoardListPaging(id, paging);
	}

	@Override
	public int getNowBoardListCount(String id) {
		return sBoardMapper.getNowBoardListCount(id);
	}



}

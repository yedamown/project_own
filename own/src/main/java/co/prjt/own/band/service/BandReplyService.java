package co.prjt.own.band.service;

import java.util.List;

import co.prjt.own.common.service.ReplyVO;

public interface BandReplyService {
	//댓글 글번호 조회
	public List<ReplyVO> getReply(String categoryNo);
	//댓글작성
	public ReplyVO insertReply(ReplyVO vo);
	//댓글삭제
	public int deleteReply(String replyNo);
	//찐삭제
	public int deleteUpdateReply(String replyNo);
	//댓글수정
	public int updateReply(ReplyVO vo);
	//댓글개별조회
	public ReplyVO reply(String replyNo);
}

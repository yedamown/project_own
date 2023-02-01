package co.prjt.own.band.mapper;

import java.util.List;

import co.prjt.own.common.service.ReReplyVO;
import co.prjt.own.common.service.ReplyVO;

public interface BandReplyMapper {
	//댓글 글번호 조회
	public List<ReplyVO> getReply(String categoryNo);
	//댓글작성
	public int insertReply(ReplyVO vo);
	//댓글삭제
	public int deleteReply(String replyNo);
	//찐삭제
	public int deleteUpdateReply(String replyNo);
	//댓글수정
	public int updateReply(ReplyVO vo);
	//댓글개별조회
	public ReplyVO reply(String categoryNo);
	
	//대댓글들 조회
	public List<ReReplyVO> getReReply(List<String> replyNos);
	//대대댓글 조회
	public List<ReReplyVO> getReReReply(List<String> rereplyNos);
}

package co.prjt.own.band.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.prjt.own.band.service.BandReplyService;
import co.prjt.own.common.service.ReplyVO;

/**
 * 
 * @author 허진주
 * 댓글...기존에 만든 모든 댓글조회하는 건 밴드쪽? 게시글쪽에 있음
 *
 */
@RestController
@RequestMapping("/own/band")
public class BandReplyController {
	
	@Autowired BandReplyService bandReplyService;
	
	@PostMapping("/reply")
	public ReplyVO insert(ReplyVO vo) {
		vo = bandReplyService.insertReply(vo);
		return vo;
	}
	//댓글수정
	@PutMapping("/reply")
	public ReplyVO update(@RequestBody ReplyVO vo) {
		bandReplyService.updateReply(vo);
		return vo;
	}
	//댓글삭제
	@DeleteMapping("/reply/{replyNo}")
		public int delete(ReplyVO vo, @PathVariable String replyNo) {
		return bandReplyService.deleteUpdateReply(replyNo);
	}
	//단건조회
	@GetMapping("/reply/{replyNo}")
		public ReplyVO reply(ReplyVO vo, @PathVariable String replyNo) {
		return bandReplyService.reply(replyNo);
	}
	//댓글확인
	@GetMapping("/reply")
	public List<ReplyVO> getReply(String categoryNo){
		List<ReplyVO> list = bandReplyService.getReply(categoryNo);
		return list;
	}
}

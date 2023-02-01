package co.prjt.own.band.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandReplyMapper;
import co.prjt.own.band.service.BandReplyService;
import co.prjt.own.common.service.ReReplyVO;
import co.prjt.own.common.service.ReplyVO;

@Service
public class BandReplyServiceImpl implements BandReplyService{
	
	@Autowired BandReplyMapper bandReplyMapper;
	

	@Override
	public List<ReplyVO> getReply(String categoryNo) {
		List<ReplyVO> voList = bandReplyMapper.getReply(categoryNo);
		//vo에서 댓글번호 뽑아오기
		List<String> replyNos = new ArrayList<String>();
			if(voList.size()>0) {
			for(int i=0; i<voList.size(); i++) {
				replyNos.add(voList.get(i).getReplyNo());
			}
			//개별댓글에 대댓글리스트구하기
			List<ReReplyVO> rere = bandReplyMapper.getReReply(replyNos);
			
			///대대댓글리스트???
			List<String> rereplyNos = new ArrayList<String>();
			if(rere.size()>0) {
				for(int i=0; i<voList.size(); i++) {
					rereplyNos.add(rere.get(i).getReReplyNo());
				}
			}
			
			List<ReReplyVO> rerere = bandReplyMapper.getReReReply(rereplyNos);
			
			//개별대댓글에 대대댓글리스트넣기
			for(int i=0; i<rere.size(); i++) {
				List<ReReplyVO> imsi = new ArrayList<ReReplyVO>();
				for(int j=0; j<rerere.size(); j++) {
					if(rerere.get(i).getReplyNo().equals(rerere.get(j).getReplyNo())) {
						imsi.add(rerere.get(j));
					}
				}
				rere.get(i).setReReplys(imsi);
			}
			
			//개별댓글에 대댓글리스트넣기
			for(int i=0; i<voList.size(); i++) {
				List<ReReplyVO> imsi = new ArrayList<ReReplyVO>();
				for(int j=0; j<rere.size(); j++) {
					if(voList.get(i).getReplyNo().equals(rere.get(j).getReplyNo())) {
						imsi.add(rere.get(j));
					}
				}
				voList.get(i).setReReplys(imsi);
			}
		}
		return voList;
	}

	@Override
	public ReplyVO insertReply(ReplyVO vo) {
		//입력이 성공했다면 조회 후 vo를 리턴
		System.out.println(vo.toString());
		int result = bandReplyMapper.insertReply(vo);
		System.out.println(vo.toString());
		if(result>0) {
			//select-key를 이용해 댓글검색
			vo.setReplyNo("gachi_"+vo.getReplyNo());
			return(bandReplyMapper.reply(vo.getReplyNo()));
		} else {
			//입력시간과 업데이트 시간이 널임
			return vo;
		}
	}

	@Override
	public int deleteReply(String replyNo) {
		return bandReplyMapper.deleteReply(replyNo);
	}
	
	@Override
	public int deleteUpdateReply(String replyNo) {
		return bandReplyMapper.deleteUpdateReply(replyNo);
	}
	
	@Override
	public int updateReply(ReplyVO vo) {
		return bandReplyMapper.updateReply(vo);
	}

	@Override
	public ReplyVO reply(String replyNo) {
		return bandReplyMapper.reply(replyNo);
	}
}

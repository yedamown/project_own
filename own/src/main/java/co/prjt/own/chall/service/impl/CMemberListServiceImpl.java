package co.prjt.own.chall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.prjt.own.chall.mapper.CAmountMapper;
import co.prjt.own.chall.mapper.CMemberListMapper;
import co.prjt.own.chall.mapper.ChallengeMapper;
import co.prjt.own.chall.service.CAmountVO;
import co.prjt.own.chall.service.CMemberListService;
import co.prjt.own.chall.service.CMemberListVO;
import co.prjt.own.chall.service.ChallengeVO;

@Component
public class CMemberListServiceImpl implements CMemberListService{

	@Autowired CMemberListMapper memlist;
	@Autowired ChallengeMapper chall;
	@Autowired CAmountMapper amount;
	
	@Override
	public int insertMemList(CMemberListVO vo) {
		//유저아이디
		String id = vo.getUserId();
		//해당도전번호
		String chNo = vo.getChallNo();
		//해당 도전번호로 검색해서 리더이름 가져오기.
		ChallengeVO cha = new ChallengeVO();
		cha.setChallNo(chNo);
		chall.getChall(cha);
		String leaderId = (chall.getChall(cha)).getChallLeader();
		System.out.println(leaderId + "리더아이디");
		System.out.println(leaderId + "리더아이디");
		if(id.equals(leaderId)) {
			vo.setMemStatus("승인");
			System.out.println(vo + "리더랑비교 o");
			return memlist.insertMemList(vo);
		} else {	
			vo.setMemStatus("대기");
			System.out.println(vo + "리더랑같지않음");
			return memlist.insertMemList(vo);
		}
	}

	@Override
	public int updateMemList(CMemberListVO vo) {
		if(vo.getMemStatus().equals("거절")) {
			String challNo = vo.getChallNo();
			String userId = vo.getUserId();
			ChallengeVO cha = new ChallengeVO();
			cha.setChallNo(challNo);
			int challPrice = (chall.getChall(cha)).getChallPrice();
			CAmountVO amt = new CAmountVO();
			amt.setChallNo(challNo);
			amt.setAmtType("도전거절");
			amt.setUserId(userId);
			amt.setPrice(challPrice);
			amount.insertAmount(amt);
		}
		return memlist.updateMemList(vo);
	}
	
	@Override
	public CMemberListVO getMemCheck(CMemberListVO vo) {
		return memlist.getMemCheck(vo);
	}

	@Override
	public List<CMemberListVO> getMemListAll(CMemberListVO vo) {
		return memlist.getMemListAll(vo);
	}

	@Override
	public int getChallMemNum(String challNo) {
		return memlist.getChallMemNum(challNo);
	}

	@Override
	public CMemberListVO applyCheck(CMemberListVO vo) {
		return memlist.applyCheck(vo);
	}


}

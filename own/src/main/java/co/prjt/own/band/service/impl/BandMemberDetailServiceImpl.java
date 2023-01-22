package co.prjt.own.band.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandMemberDetailMapper;
import co.prjt.own.band.service.BandMemberDetailService;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.chat.service.MessageVO;

@Service
public class BandMemberDetailServiceImpl implements BandMemberDetailService{
	@Autowired BandMemberDetailMapper bandMemberDetailMapper;
	
	@Override
	public List<BandMemberDetailVO> bandMemberList(BandMemberDetailVO vo) {
		// 밴드 회원 목록 띄우기 + 채팅방 생성여부
		return bandMemberDetailMapper.bandMemberList(vo);
	}

	@Override
	public String getBandMemberNickname(MessageVO vo) {
		// 밴드멤버식별번호로 닉네임 가져오기.
		return bandMemberDetailMapper.getBandMemberNickname(vo);
	}

}

package co.prjt.own.band.mapper;

import java.util.List;

import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.chat.service.MessageVO;

public interface BandMemberDetailMapper {
		// 밴드 회원 정보 + 채팅방 생성여부 리스트
		List<BandMemberDetailVO> bandMemberList(BandMemberDetailVO vo); 
		
		// 밴드번호 + 유저아이디로 밴드멤버번호 가져오기.
		String getBandMemberNo(BandMemberDetailVO vo);
		
		// 밴드멤버식별번호로 닉네임 가져오기.
		String getBandMemberNickname(MessageVO vo);
}

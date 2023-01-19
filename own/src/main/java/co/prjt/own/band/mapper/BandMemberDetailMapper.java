package co.prjt.own.band.mapper;

import java.util.List;

import co.prjt.own.band.service.BandMemberDetailVO;

public interface BandMemberDetailMapper {
	// 밴드 회원 정보 + 채팅방 생성여부 리스트
		List<BandMemberDetailVO> bandMemberList(BandMemberDetailVO vo); 
}

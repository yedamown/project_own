package co.prjt.own.band.service;

import java.util.List;

public interface BandMemberDetailService {
	// 밴드 회원 정보 + 채팅방 생성여부 리스트
	List<BandMemberDetailVO> bandMemberList(BandMemberDetailVO vo); 
		
	
}

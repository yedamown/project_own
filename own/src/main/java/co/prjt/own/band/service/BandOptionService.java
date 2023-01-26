package co.prjt.own.band.service;

import java.util.List;

public interface BandOptionService {
	// 가치 설정 - 멤버 관리 - 전체 멤버 리스트
	List<BandMemberDetailVO> bandOptionGetAllMemberList(BandMemberDetailVO vo);
}

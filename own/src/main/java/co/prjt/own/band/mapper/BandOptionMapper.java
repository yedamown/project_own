package co.prjt.own.band.mapper;

import java.util.List;

import co.prjt.own.band.service.BandMemberDetailVO;

import co.prjt.own.band.service.BandVO;

public interface BandOptionMapper {
	//해당 밴드에 관련된 사람들을 관리하기위한 밴드메인관리페이지.
		public List<BandMemberDetailVO> bandManageHome(BandMemberDetailVO vo);
		//해당 밴드에 가입한 사람들을 카운팅
		public int bandCount(BandMemberDetailVO vo);
		//해당 밴드 정보
		public BandVO bandInfo(BandVO vo);
		//밴드 업데이트 제발 되라
		public int bandUpdate(BandVO vo);
		// 밴드 멤버 리스트
		public List<BandMemberDetailVO> bandOptionGetAllMemberList(BandMemberDetailVO vo);
}
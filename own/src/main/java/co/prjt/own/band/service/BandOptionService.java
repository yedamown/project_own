package co.prjt.own.band.service;

import java.util.List;

import co.prjt.own.common.Paging;
import co.prjt.own.ownhome.service.OwnUserVO;

public interface BandOptionService {
	// 해당 밴드에 관련된 사람들을 관리하기위한 밴드메인관리페이지.
	public List<BandMemberDetailVO> bandManageHome(BandMemberDetailVO vo);

	// 해당 밴드에 가입한 사람들을 카운팅
	public int bandCount(BandMemberDetailVO vo);

	// 해당 밴드 정보
	public BandVO bandInfo(BandVO vo);

	// 밴드 업데이트 제발 되라
	public int bandUpdate(BandVO vo);

	// 밴드위임..
	public int bandPass(BandVO vo);

	// 밴드 휴면진입..
	public int bandHuman(String bandNo);

	// 밴드 휴면 풀기..
	public int bandDisHuman(String bandNo);
  
	// 밴드 가입한 멤버 리스트
	//public List<BandMemberDetailVO> bandOptionGetAllMemberList(BandMemberDetailVO vo, Paging paging);

	// 밴드 가입대기중 멤버 리스트
	public List<BandMemberDetailVO> bandOptionGetWaitingMemberList(BandMemberDetailVO vo, Paging paging);

	// 밴드 강퇴된 회원리스트
	public List<BandMemberDetailVO> bandOptionGetkickedMemberList(BandMemberDetailVO vo, Paging paging);

}

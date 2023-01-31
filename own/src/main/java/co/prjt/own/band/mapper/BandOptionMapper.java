package co.prjt.own.band.mapper;

import java.util.List;

import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.band.service.BandVO;
import co.prjt.own.common.Paging;

public interface BandOptionMapper {

	// 해당 밴드에 관련된 사람들을 관리하기위한 밴드메인관리페이지.
	public List<BandMemberDetailVO> bandManageHome(BandMemberDetailVO vo);

	// 해당 밴드에 가입한 사람들을 카운팅
	public int bandCount(BandMemberDetailVO vo);

	// 해당 밴드 정보
	public BandVO bandInfo(BandVO vo);

	// 밴드 업데이트 제발 되라
	public int bandUpdate(BandVO vo);

	// 밴드위임
	public int bandPass(BandVO vo);

	// 밴드 휴면진입..
	public int bandHuman(String bandNo);

	// 밴드 휴면 풀기..
	public int bandDisHuman(String bandNo);

	// 밴드 가입한 멤버 리스트
	public List<BandMemberDetailVO> bandOptionGetAllMemberList(BandMemberDetailVO vo);

	// 밴드 가입대기중 멤버 리스트
	public List<BandMemberDetailVO> bandOptionGetWaitingMemberList(BandMemberDetailVO vo);

	// 밴드 강퇴된 회원리스트
	public List<BandMemberDetailVO> bandOptionGetkickedMemberList(BandMemberDetailVO vo);

	// 밴드 멤버 상태 업데이트
	public int updateBandMemberStatus(BandMemberDetailVO vo);
	
	// 모달에 띄울 밴드 멤버 상세 정보
	public List<BandMemberDetailVO> selectBandMemberInfo(BandMemberDetailVO vo);

}
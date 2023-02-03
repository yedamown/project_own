package co.prjt.own.band.mapper;

import java.util.List;

import co.prjt.own.band.service.BandMemberDefaultVO;
import co.prjt.own.band.service.BandMemberDetailVO;

public interface BandMemberDefaultMapper {
	//오운아이디로 밴드 디폴트 유저 검색	
	public BandMemberDefaultVO getBandMemberDefault(String userId);
	//수정
	public int updateMemberDf(BandMemberDefaultVO vo);
	//입력
	public int insertDefault(BandMemberDefaultVO vo);
	//내 밴드들 조회
	public List<BandMemberDetailVO> getMyBandOption(String userId);
	//가치 탈퇴
	public int myBandLeave(String userId, String bandNo);
	//가치 신청철회
	public int myBandLeave2(String userId, String bandNo);
}

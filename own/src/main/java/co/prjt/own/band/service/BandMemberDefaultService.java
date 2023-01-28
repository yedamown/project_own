package co.prjt.own.band.service;

public interface BandMemberDefaultService {
	//오운아이디로 밴드 디폴트 유저 검색	
	public BandMemberDefaultVO getBandMemberDefault(String userId);
	//수정
	public int updateMemberDf(BandMemberDefaultVO vo);
	//입력
	public int insertDefault(BandMemberDefaultVO vo);
}

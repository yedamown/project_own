package co.prjt.own.band.service;

import java.util.List;

import co.prjt.own.chat.service.MessageVO;

public interface BandMemberDetailService {
	// 밴드 회원 정보 + 채팅방 생성여부 리스트
	List<BandMemberDetailVO> bandMemberList(BandMemberDetailVO vo);

	// 밴드번호 + 유저아이디로 밴드멤버번호 가져오기.
	String getBandMemberNo(BandMemberDetailVO vo);
	
	// 밴드멤버식별번호로 닉네임 가져오기.
	String getBandMemberNickname(MessageVO vo);
	
	
	//밴드번호+유저아이디로 유저값가져오기
	public BandMemberDetailVO getBandMemberDetail(BandMemberDetailVO vo);
	//유저 회원가입
	public BandMemberDetailVO insertBandMemberDetail(BandMemberDetailVO vo);
	//프로필사진없을 시 임의의 사진 입력 value는 식별키
	public int bandProfilImg(String value);
	//프로필사진없을 시 디폴트 사진 입력
	public int bandProfilDefImg(String defaultNo, String detailNo, String mediaServerFile);
	//가입시 닉네임중복체크
	public int duplicateChk(String bandNo, String nickName);
	//밴드설정 업데이트
	public int myOptionUpdate(BandMemberDetailVO vo);
}

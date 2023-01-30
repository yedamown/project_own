package co.prjt.own.band.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.chat.service.MessageVO;

public interface BandMemberDetailMapper {
		// 밴드 회원 정보 + 채팅방 생성여부 리스트
		List<BandMemberDetailVO> bandMemberList(BandMemberDetailVO vo); 
		
		// 밴드번호 + 유저아이디로 밴드멤버번호 가져오기.
		String getBandMemberNo(BandMemberDetailVO vo);
		
		// 밴드멤버식별번호로 닉네임 가져오기.
		String getBandMemberNickname(MessageVO vo);
		
		
		//밴드번호+유저아이디로 유저값가져오기
		public BandMemberDetailVO getBandMemberDetail(BandMemberDetailVO vo);
		//유저 회원가입
		public int insertBandMemberDetail(BandMemberDetailVO vo);
		//프로필사진없을 시 임의의 사진 입력 value는 식별키
		public int bandProfilImg(String mediaNo, String value);
		//프로필사진없을 시 디폴트 사진 입력
		public int bandProfilDefImg(@Param("defaultNo") String defaultNo, @Param("detailNo") String detailNo, @Param("mediaServerFile") String mediaServerFile);
		//가입시 닉네임중복체크
		public int duplicateChk(String bandNo, String nickName);
		//밴드 내 설정변경
		public int myOptionUpdate(BandMemberDetailVO vo);
		//아이디로 가치별명과 프로필사진들을 가져옴(list<String>)
		public List<BandMemberDetailVO> getBandMemberNickProfile(BandMemberDetailVO vo);
}

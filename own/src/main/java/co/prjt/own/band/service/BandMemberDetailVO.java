package co.prjt.own.band.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.prjt.own.common.service.MultimediaVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BandMemberDetailVO {
	String userId;
	String bandNickname;
	String bandNo;
	String bandMemberStatus;
	String bandKickStatus;
	String bandMemberIntro;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
	Date bandSignupDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
	Date bandAccessDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
	Date bandKickDate;
	String bandMemberNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
	Date bandBirth;
	String bandGender;
	String bandName;
	
	Integer first = 1;
	Integer last = 10;
	
	//추가
	public String bandMemberRejoin;
	public String bandMemberKickReason;
	public String bandMemberGenderOpen;
	public String bandMemberBirthOpen;
	public String bandMemberAgeOpen ;
	
	// not in DB
	int chatCheck; // 로그인 유저와 상대 멤버와의 채팅방 생성유무
	int boradCnt; // 회원의 밴드내 게시글 수 
	int replyCnt; // 회원의 밴드내 댓글 수
	int age; // 생년월일로 계산한 회원의 나이
	
	//이미지
	public MultimediaVO detailImg;
	public List<MultimediaVO> detailImgs;
}

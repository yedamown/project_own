package co.prjt.own.band.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import co.prjt.own.common.Paging;
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

	public String userId;
	public String bandNickname;
	public String bandNo;
	public String bandMemberStatus;
	public String bandKickStatus;
	public String bandMemberIntro;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	public Date bandSignupDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	public Date bandAccessDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	public Date bandKickDate;
	public String bandMemberNo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	public Date bandBirth;
	public String bandGender;
	public String bandName;
	
	public Paging paging;
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
	int boardCnt; // 회원의 밴드내 게시글 수 
	int replyCnt; // 회원의 밴드내 댓글 수
	int age; // 생년월일로 계산한 회원의 나이
	String searchOption; // 정렬옵션
	  
    // 밴드멤버프로필이미지
   public String profileImg;
   public MultimediaVO detailImg;
   public List<MultimediaVO> detailImgs;
   
}

package co.prjt.own.band.service;

import java.util.Date;

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
	Date bandSignupDate;
	Date bandAccessDate;
	Date bandKickDate;
	String bandMemberNo;
	Date bandBirth;
	String bandGender;
	String bandName;
	
	Integer first = 1;
	Integer last = 10;

	// not in DB
	int chatCheck;
}

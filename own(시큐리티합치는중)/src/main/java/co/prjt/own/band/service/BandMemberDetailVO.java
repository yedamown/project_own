package co.prjt.own.band.service;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
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
	

	Integer first = 1;
	Integer last = 10;
}

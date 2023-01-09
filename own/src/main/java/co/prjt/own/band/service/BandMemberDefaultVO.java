package co.prjt.own.band.service;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BandMemberDefaultVO {
	String userId;
	String bandNickname;
	String bandGender;
	String bandLocation;
	String bandInterest;
	Date bandBirth;
	

	Integer first = 1;
	Integer last = 10;
}

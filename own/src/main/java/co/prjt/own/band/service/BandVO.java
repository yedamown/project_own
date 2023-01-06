package co.prjt.own.band.service;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BandVO {
	String bandNo;
	String bandLeaderid;
	String bandName;
	String bandIntro;
	String bandKeyword;
	String bandOpenOption;
	String bandLocOption;
	Date bandAgeBeforoption;
	String  bandAgeAfteroption;
	String bandCategory;
	String bandGenderOption;
	String bandMembershipOption;
	String bandLocation;
	
	List<String> bnos;
	Integer first = 1;
	Integer last = 10;
}

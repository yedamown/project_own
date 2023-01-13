package co.prjt.own.band.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	String bandAgeOption;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
	Date bandAgeBeforoption;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
	Date  bandAgeAfteroption;
	String bandCategory;
	String bandGenderOption;
	String bandMembershipOption;
	String bandLocation;
	String bandGender;
	
	//bandNo의 집합
	List<String> bandNos;
	
	Integer first = 1;
	Integer last = 10;
}

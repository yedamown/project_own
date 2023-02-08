package co.prjt.own.band.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.prjt.own.common.Paging;
import co.prjt.own.common.service.MultimediaVO;
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
	//밴드이미지주소
	String mediaServerFile;
	//밴드 대표이미지 주소
	MultimediaVO bandMainImg;
	//임시로추가했습니다..
	String bandStatus;
	Paging Paging;
	String memcount;
	//bandNo의 집합
	List<String> bandNos;
	
	Integer first = 1;
	Integer last = 10;
}

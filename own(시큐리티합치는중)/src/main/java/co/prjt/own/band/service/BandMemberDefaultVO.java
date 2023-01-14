package co.prjt.own.band.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
	Date bandBirth;
	

	Integer first = 1;
	Integer last = 10;
}

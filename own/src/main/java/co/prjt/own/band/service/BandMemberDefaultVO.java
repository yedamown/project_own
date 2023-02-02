package co.prjt.own.band.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.prjt.own.common.service.MultimediaVO;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	Date bandBirth;

	Integer first = 1;
	Integer last = 10;

	//이미지 
	public MultimediaVO defaultImg;
	public String defImg;
	//new FormData로 집어넣는 이미지(ajax)
    private MultipartFile attachFile;
    private String mediaServerFile;
}

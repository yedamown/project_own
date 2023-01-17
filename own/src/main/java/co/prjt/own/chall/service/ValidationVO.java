package co.prjt.own.chall.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ValidationVO {
	String vldNo; 
	String challNo;
	String userId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date vldDate;
	
	String vldStatus;

	//이미지이름 넣는 곳 ㅎ 조인해서 넣을 곳
	String mediaServerFile;
}

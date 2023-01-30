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
	
	//내 인증횟수 넣기
	int vldCount;
	//도전 전체횟수 넣기
	int challVldCount;
	
	int startToToday;
	
	int beforeWeek;
	int nowWeek;
	
	//인증횟수 카운트
	int myVld;
	int memVldAvg;
	
	
}

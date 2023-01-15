package co.prjt.own.chall.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChallengeVO {
	String challNo;
	String challCategory;
	String challTag;
	String challTitle;
	int challDuration;
	int challFreq;
	int challPrice;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date challStartdate;
	
	int nowMember;
	int maxMember;
	String challIntro;
	String vldMethod;
	String challPwd;
	String challStatus;
	String challLeader;
	String restPrice;
	
	String firstImage;
}

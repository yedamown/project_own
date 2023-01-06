package co.prjt.own.chall.service;

import java.util.Date;

import lombok.Data;

@Data
public class ChallVO {
	String challNo;
	String challCategory;
	String challTag;
	String challTitle;
	int challDuration;
	int challFreq;
	Date challStartdate;
	int nowMember;
	int maxMember;
	String challIntro;
	String vldMethod;
	String challPwd;
	String challStatus;
	String challLeader;
	String restPrice;
}

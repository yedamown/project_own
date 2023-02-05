package co.prjt.own.chall.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import co.prjt.own.common.Paging;
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
	
	int nowMem;
	int maxMem;
	String challIntro;
	String vldMethod;
	String challPwd;
	String challStatus;
	String challLeader;
	String restPrice;

	//멀티미디어 정보가져올 곳
	String firstImage;
	//내 도전 검색에서 내 아이디 담아둘 곳
	String userId;
	//도전검색시 회원상태...
	String memStatus;
	
	//페이징관련정보
	int first;
	int last;
	Paging paging;
	
	//검색할 단어 담아둘 곳
	String searchWord;
}

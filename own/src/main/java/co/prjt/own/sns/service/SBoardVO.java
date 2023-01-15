package co.prjt.own.sns.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SBoardVO {
	String snsBoardNo;
	String snsBoardContent;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	Date snsBoardDate;
	String snsNickname;
	String snsAccountNo;
	public String getSnsBoardNo() {
		return snsBoardNo;
	}
	
	int count;
	
}

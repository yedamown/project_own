package co.prjt.own.sns.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class StoryVO {
	String snsStoryNo;
	String snsNickname;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	Date snsStoryDate;
	String snsSatus;
	String snsAccountNo;
	String mediaServerFile;
	
}

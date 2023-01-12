package co.prjt.own.sns.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SBoardVO {
	String snsBoardNo;
	String snsBoardContent;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	Date snsBoardDate;
	String snsNickname;
	String snsAccountNo;
	
}

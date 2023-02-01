package co.prjt.own.sns.service;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.prjt.own.common.Paging;
import co.prjt.own.common.service.MultimediaVO;
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
	List<MultimediaVO> fileList;
	String mediaServerFile;
	String mediaNo;
	String likeCount;
	String replyCount;
}

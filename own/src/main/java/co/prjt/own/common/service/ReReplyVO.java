package co.prjt.own.common.service;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReReplyVO {
	public String reReplyNo;
	public String reReplyContent;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	Date reReplyDate;
	public String userId;
	public String replyNo;
	//hjj추가
	public String nickName;
	public String profileImg;
	
	List<ReReplyVO> reReplys;
}

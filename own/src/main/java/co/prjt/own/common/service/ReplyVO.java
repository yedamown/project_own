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
public class ReplyVO {
	public String replyNo;
	public String replyContent;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	Date replyDate;
	public String userId;
	public int replyLike;
	public String categoryNo;
	public String snsNickname;
	
	//hjj추가
	public String nickName;
	public String profileImg;
	List<ReReplyVO> reReplys;
}

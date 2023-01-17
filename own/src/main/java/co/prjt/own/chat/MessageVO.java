package co.prjt.own.chat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MessageVO {
	String messageNo;
	String messageContent;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	Date messageTime;
	String chatroomNo;
	String bandMemberNo;
}

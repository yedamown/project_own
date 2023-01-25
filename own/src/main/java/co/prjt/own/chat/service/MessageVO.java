package co.prjt.own.chat.service;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MessageVO {
	private String messageNo; // 메세지 번호
	private String chatroomNo; // 채팅방 번호
	private String bandMemberNo; // 메세지를 보낸사람(밴드멤버식별번호) 번호
	private String messageContent; // 메세지 내용
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp  messageTime; // 메세지 발송 시간
	//not in DB
	String bandNickname; // 메세지를 보낸사람(밴드멤버식별번호) 닉네임
	int mno;
}

package co.prjt.own.chat.service;

import lombok.Data;

@Data
public class ChatroomVO {
	private String chatroomNo; // 채팅방 식별번호
    private String chatroomName; // 채팅방 이름
    private String bandMemberNo; // 채팅방 참여자 식별번호 

    //not in DB
    private String bandMemberNo1; // 채팅방 상대 참여자 식별번호
    private String profileImg; // 채팅 상대방 프사
}

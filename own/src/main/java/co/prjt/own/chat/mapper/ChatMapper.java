package co.prjt.own.chat.mapper;

import java.util.List;

import org.springframework.web.socket.WebSocketSession;

import co.prjt.own.chat.service.ChatroomVO;
import co.prjt.own.chat.service.MessageVO;

public interface ChatMapper {
	// 전체 채팅방 목록 중 해당 멤버의 식별번호로 개설된 채팅방 목록 출력
	List<ChatroomVO> chatroomList(String bandMemberNo);

	// 채팅방 개설
	int createChatroom(List<ChatroomVO> list);

	// 메세지 DB에 저장
	int saveMessage(MessageVO dto);

	// 메세지 DB에서 조회
	List<MessageVO> getMessage(MessageVO dto);

	// 웹 소켓 세션에 메세지 저장.
	public <T> void sendMessage(WebSocketSession session, T message);
}

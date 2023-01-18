package co.prjt.own.chat.service;

import java.util.List;

import org.springframework.web.socket.WebSocketSession;

public interface ChatService {
	// 전체 채팅방 목록 중 해당 식별번호로 개설된 채팅방 목록 출력 
	List<ChatroomVO> chatroomList(String bandMemberNo);
	
	// 1:1 채팅방 찾기
	
	/* 채팅방 개설(1:1)
	 * 전체 채팅방은 방개설 필요없이 구역을 만들어 메세지만 띄우면 된다. 
	 */
	int createChatroom(List<ChatroomVO> list);

	// 메세지 DB에 저장
	int saveMessage(MessageVO vo);
	
	// 메세지 DB에서 조회, 메세지 여러개 뽑아 오니까 List타입
	List<MessageVO> getMessage(MessageVO vo);
	
	// 웹 소켓 세션에 메세지 저장.
	public <T> void sendMessage(WebSocketSession session, T message);
}
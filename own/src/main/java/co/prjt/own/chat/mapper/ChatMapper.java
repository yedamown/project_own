package co.prjt.own.chat.mapper;

import java.util.List;

import org.springframework.web.socket.WebSocketSession;

import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.chat.service.ChatroomVO;
import co.prjt.own.chat.service.MessageVO;

public interface ChatMapper {
	// 밴드멤버번호로 생성된 모든 채팅방 정보 가져오기
	List<ChatroomVO> getMyChatroomList(String userId);

	//채팅방 번호 가져온 후, 채팅방 개설(1:1) 전체 채팅방은 방개설 필요없이 구역을 만들어 메세지만 띄우면 된다.
	String createChatroomNo();

	int createChatroom(ChatroomVO vo);

	// 기존 채팅방번호 가져옴.
	String findChatroomNo(ChatroomVO vo);

	// 메세지 DB에 저장
	int saveMessage(MessageVO vo);

	// 메세지 DB에서 조회, 메세지 여러개 뽑아 오니까 List타입
	List<MessageVO> getMessage(String chatroomNo);

	// 웹 소켓 세션에 메세지 저장.
	public <T> void sendMessage(WebSocketSession session, T message);
	
	// 채팅방 정보 가져옴
	List<ChatroomVO> getChatroomInfo(ChatroomVO vo);

}

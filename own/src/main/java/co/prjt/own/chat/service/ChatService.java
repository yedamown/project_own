package co.prjt.own.chat.service;

import java.util.List;

public interface ChatService {

	// 밴드멤버번호로 생성된 모든 채팅방 정보 가져오기
	List<ChatroomVO> getMyChatroomList(String userId);
	
	/* 신규 채팅방 개설(1:1)
	 */
   String createChatroom(List<ChatroomVO> list);

   // 기존 채팅방번호 가져옴.
   String findChatroomNo(ChatroomVO vo);
   
   // 메세지 DB에 저장
   int saveMessage(MessageVO vo);
   
   // 메세지 DB에서 조회, 메세지 여러개 뽑아 오니까 List타입
   List<MessageVO> getMessage(String chatroomNo);
   
   // 채팅방 정보 가져옴
   List<ChatroomVO> getChatroomInfo(ChatroomVO vo);

}
package co.prjt.own.chat.service;

import java.util.List;

import co.prjt.own.band.service.BandMemberDetailVO;

public interface ChatService {
	// 로그인 아이디로 가입중인 밴드 멤버번호 받아오기
	List<BandMemberDetailVO> getMyBandMemberNo(String userId);
	
	// 전체 채팅방 목록 중 해당 식별번호로 개설된 채팅방 목록 출력 
	List<ChatroomVO> myChatroomList(String bandMemberNo);
	
	/* 신규 채팅방 개설(1:1)
	 */
	String createChatroom(List<ChatroomVO> list);

	// 기존 채팅방번호 가져옴.
	String findChatroomNo(ChatroomVO vo);
	
	// 메세지 DB에 저장
	int saveMessage(MessageVO vo);
	
	// 메세지 DB에서 조회, 메세지 여러개 뽑아 오니까 List타입
	List<MessageVO> getMessage(String chatroomNo);

}

package co.prjt.own.chat.service;

import java.util.List;

import co.prjt.own.band.service.BandMemberDetailVO;

public interface ChatService {
	// 로그인 아이디로 가입중인 밴드 멤버번호 받아오기
	List<BandMemberDetailVO> getMyBandMemberNoList(String userId);
	
	// 밴드멤버번호로 생성된 모든 채팅방 정보 가져오기
	List<ChatroomVO> getMyChatroomList(List<BandMemberDetailVO> list);
	
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

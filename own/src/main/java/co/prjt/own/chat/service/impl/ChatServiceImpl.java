package co.prjt.own.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.chat.mapper.ChatMapper;
import co.prjt.own.chat.service.ChatService;
import co.prjt.own.chat.service.ChatroomVO;
import co.prjt.own.chat.service.MessageVO;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	ChatMapper chatMapper;

	@Override
	public List<ChatroomVO> getMyChatroomList(String userId) {
		// 밴드멤버번호로 생성된 모든 채팅방 정보 가져오기
		return chatMapper.getMyChatroomList(userId);
	}

	@Override
	public String createChatroom(List<ChatroomVO> list) {
		// 채팅방 개설
		String cNo = chatMapper.createChatroomNo(); // 채팅방 시퀀스 번호
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setChatroomNo(cNo);
			chatMapper.createChatroom(list.get(i));
		}
		return cNo;
	}

	@Override
	public String findChatroomNo(ChatroomVO vo) {
		// 기존 채팅방번호 가져옴.
		return chatMapper.findChatroomNo(vo);
	}

	@Override
	public int saveMessage(MessageVO vo) {
		// 메세지 DB에 저장
		return chatMapper.saveMessage(vo);
	}

	@Override
	public List<MessageVO> getMessage(String chatroomNo) {
		// 메세지 DB에서 조회, 메세지 여러개 뽑아 오니까 List타입
		return chatMapper.getMessage(chatroomNo);
	}

	@Override
	public List<ChatroomVO> getChatroomInfo(ChatroomVO vo) {
		// 채팅방 정보 가져옴
		return chatMapper.getChatroomInfo(vo);
	}
}

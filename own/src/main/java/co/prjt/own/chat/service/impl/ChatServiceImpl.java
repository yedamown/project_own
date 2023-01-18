package co.prjt.own.chat.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.prjt.own.chat.mapper.ChatMapper;
import co.prjt.own.chat.service.ChatService;
import co.prjt.own.chat.service.ChatroomVO;
import co.prjt.own.chat.service.MessageVO;	

public class ChatServiceImpl implements ChatService {
	private final ObjectMapper mapper = new ObjectMapper();
	@Autowired
	ChatMapper chatMapper;

	@Override
	public List<ChatroomVO> chatroomList(String bandMemberNo) {
		// 전체 채팅방 목록 중 해당 식별번호로 개설된 채팅방 목록 출력 
		return chatMapper.chatroomList(bandMemberNo);
	}

	@Override
	public int createChatroom(List<ChatroomVO> list) {
		// 채팅방 개설
		return chatMapper.createChatroom(list);
	}

	@Override
	public int saveMessage(MessageVO vo) {
		// 메세지 DB에 저장
		return chatMapper.saveMessage(vo);
	}

	@Override
	public List<MessageVO> getMessage(MessageVO vo) {
		// 메세지 DB에서 조회, 메세지 여러개 뽑아 오니까 List타입
		return getMessage(vo);
	}

	@Override
	public <T> void sendMessage(WebSocketSession session, T message) {
		// 웹 소켓 세션에 메세지 저장.
		try{
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        } catch (IOException e) {
            //log.error(e.getMessage(), e);
        }

	}

}

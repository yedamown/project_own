package co.prjt.own.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prjt.own.band.mapper.BandMemberDefaultMapper;
import co.prjt.own.band.service.BandMemberDetailVO;
import co.prjt.own.chat.mapper.ChatMapper;
import co.prjt.own.chat.service.ChatService;
import co.prjt.own.chat.service.ChatroomVO;
import co.prjt.own.chat.service.MessageVO;

@Service
public class ChatServiceImpl implements ChatService {
	@Autowired
	ChatMapper chatMapper;
	@Autowired
	BandMemberDefaultMapper bandMemberDefaultMapper;

	@Override
	public List<BandMemberDetailVO> getMyBandMemberNo(String userId) {
		// 로그인 아이디로 가입중인 밴드 멤버번호 받아오기
		return bandMemberDefaultMapper.getMyBandOption(userId);
	}
	
	@Override
	public List<ChatroomVO> myChatroomList(String bandMemberNo) {
		// 전체 채팅방 목록 중 해당 식별번호로 개설된 채팅방 목록 출력
		return chatMapper.chatroomList(bandMemberNo);
	}

	@Override
	public String createChatroom(List<ChatroomVO> list) {
		// 채팅방 개설
		System.out.println("serviceImpl===========" + list);
		String cNo = chatMapper.createChatroomNo(); // 가져온 시퀀스 번호
		System.out.println("룸넘버===========" + cNo);
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

	

}

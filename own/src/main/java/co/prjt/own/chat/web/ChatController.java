package co.prjt.own.chat.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import co.prjt.own.band.service.BandMemberDetailService;
import co.prjt.own.chat.service.ChatService;
import co.prjt.own.chat.service.ChatroomVO;
import co.prjt.own.chat.service.MessageVO;

@Controller
public class ChatController {
	@Autowired
	BandMemberDetailService bandMemberDetailService;
	@Autowired
	ChatService chatService;
	@Autowired
	private SimpMessagingTemplate template;

	/*
	 * DB에 채팅방 생성(채팅방 시퀀스 번호 받아오고 -> 방만들기)
	 */
	@PostMapping("/createChatroom")
	@ResponseBody
	public String createChatroom(@RequestBody List<ChatroomVO> list) {
		System.out.println("채팅방 list===========" + list);
		return chatService.createChatroom(list);
	}
	
	// 기존 채팅방 번호 가져오기 
	@PostMapping("/findChatroomNo")
	@ResponseBody
	public String findChatroomNo(@RequestBody ChatroomVO vo) {
		System.out.println("채팅방 번호===========" + vo);
		return chatService.findChatroomNo(vo);
	}
	
	
	// 채팅 페이지 이동
	@PostMapping("/chatroom")
	public String chatPage() {
		return "content/chat/chatroom";
	}

	// 채팅 입장 메세지
	@MessageMapping("/chat/enter")
	public void greeting(MessageVO vo) throws Exception {
		System.out.println("채팅 입장성공===========" + vo);
		// 받아온 메세지 객체에서 밴드멤버식별번호를 가져와 닉네임 set
		vo.setBandNickname(bandMemberDetailService.getBandMemberNickname(vo));
		vo.setMessageContent(vo.getBandNickname() + "님이 채팅방에 참여하였습니다.");
		// 해당 방으로 메세지 전송
		template.convertAndSend("/subscribe/chat/room/" + vo.getChatroomNo(), vo);
	}

	// 채팅 메세지
	@MessageMapping("/chat/message")
	public void message(MessageVO vo) throws Exception {
		// 메세지 DB에 저장
		chatService.saveMessage(vo);
		// 해당 방으로 메세지 전송
		template.convertAndSend("/subscribe/chat/room/" + vo.getChatroomNo(), vo);
	}

}

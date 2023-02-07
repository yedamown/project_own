package co.prjt.own.chat.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
      return chatService.createChatroom(list);
   }

	// 밴드멤버번호로 생성된 모든 채팅방 정보 가져오기
	@GetMapping("/getMyChatroomList")
	@ResponseBody
	public List<ChatroomVO> getMyChatroomList(@RequestParam String userId) {
		return chatService.getMyChatroomList(userId);
	}
	
	// 기존 채팅방 번호 가져오기 
	@PostMapping("/findChatroomNo")
	@ResponseBody
	public String findChatroomNo(@RequestBody ChatroomVO vo) {
		System.out.println("채팅방 번호===========" + vo);
		return chatService.findChatroomNo(vo);
	}
	
	
	// 채팅 페이지 이동
	@RequestMapping(value="/chatroom", method=RequestMethod.GET)
	public String chatPage(ChatroomVO vo, Model model) {
		model.addAttribute("chatInfo", chatService.getChatroomInfo(vo));
		return "content/chat/chatroom";
	}

   // 채팅 입장 메세지
   @MessageMapping("/chat/room/enter")
   public void greeting(MessageVO vo) throws Exception {
      System.out.println("채팅 입장성공===========" + vo);
      // 받아온 메세지 객체에서 밴드멤버식별번호를 가져와 닉네임 set
      vo.setBandNickname(bandMemberDetailService.getBandMemberNickname(vo));
      vo.setMessageContent(vo.getBandNickname() + "님이 채팅방에 참여하였습니다.");
      // 해당 방으로 메세지 전송
      template.convertAndSend("/subscribe/chat/room/" + vo.getChatroomNo(), vo);
   }

   // 채팅 메세지
   @MessageMapping("/chat/room")
   public void message(MessageVO vo) throws Exception {
      // 메세지 DB에 저장
      chatService.saveMessage(vo);
      // 해당 방으로 메세지 전송
      // 구독 주소랑 같아야함 
      template.convertAndSend("/subscribe/chat/room/" + vo.getChatroomNo(), vo);
   }
   
   // 채팅방의 지난 메세지 불러오기
   @GetMapping("/getMessage")
   @ResponseBody
   public List<MessageVO> getMessage(@RequestParam String chatroomNo) {
      return chatService.getMessage(chatroomNo);
   }
}
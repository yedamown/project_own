package co.prjt.own.chat.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.prjt.own.chat.mapper.ChatMapper;

@Controller
@RequestMapping("/own")
public class ChatController {
	@Autowired
	ChatMapper mapper;

	/*
	 * 1:1 채팅하기(밴드 내 멤버목록에서 채팅하기 클릭 시 해당 멤버와 기존 채팅방이 있는지 검색하고 있을시 기존 채팅방이 띄워지고 없을 시
	 * 채팅방 생성
	 */

	// 채팅 페이지 이동
	@RequestMapping(value = "/chatTest", method = RequestMethod.GET)
	public String chatPage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		// OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
		// 세션에 담긴 아이디를 모델에 저장시켜 보냄
		// System.out.println(user.getUserId());
		// model.addAttribute("userId", user.getUserId());
		return "content/chat/chatTest";
	}
	
	// 채팅 페이지 이동 2
		@RequestMapping(value = "/chat", method = RequestMethod.GET)
		public String chatPage2(HttpServletRequest request, Model model) {
			HttpSession session = request.getSession();
			// OwnUserVO user = (OwnUserVO) session.getAttribute("loginUser");
			// 세션에 담긴 아이디를 모델에 저장시켜 보냄
			// System.out.println(user.getUserId());
			// model.addAttribute("userId", user.getUserId());
			return "content/chat/chat";
		}

	/*
	 * @MessageMapping("/chat") public void send(ChatRoom chatRoom) throws
	 * IOException { chatRoom.setSendTime(TimeUtils.getCurrentTimeStamp()); //
	 * append message to txtFile chatRoomService.appendMessage(chatRoom);
	 * 
	 * int id = chatRoom.getId(); String url = "/user/" + id + "/queue/messages";
	 * simpMessage.convertAndSend(url, new ChatRoom(chatRoom.getContent(),
	 * chatRoom.getSenderName(), chatRoom.getSendTime())); }
	 */
}

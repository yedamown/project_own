package co.prjt.own.chat.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import co.prjt.own.chat.service.ChatService;
import co.prjt.own.chat.service.ChatroomVO;
import co.prjt.own.chat.service.Greeting;
import co.prjt.own.chat.service.HelloMessage;
import co.prjt.own.ownhome.service.OwnUserVO;

@Controller
public class ChatController {
	@Autowired
	ChatService chatService;

	/*
	 * 1:1 채팅(밴드 내 멤버목록에서 기존 채팅 목록이 없는 경우 채팅방 생성 채팅방 생성 하기
	 */
	@PostMapping("/createChatroom")
	@ResponseBody
	public String createChatroom(@RequestBody List<ChatroomVO> list) {
		return chatService.createChatroom(list);
	}

	// 채팅 페이지 이동
	@RequestMapping(value = "/chatroom", method = RequestMethod.GET)
	public String chatPage() {
		return "content/chat/chatting";
	}

	// 채팅 시작 환영메세지
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting1(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting(HtmlUtils.htmlEscape(message.getName()) + "님이 입장하셨습니다.");
	}
}

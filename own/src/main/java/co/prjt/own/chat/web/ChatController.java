package co.prjt.own.chat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.prjt.own.chat.mapper.ChatMapper;

@Controller
@RequestMapping("/own")
public class ChatController {

	@Autowired
	ChatMapper mapper;
	
	/*1:1 채팅하기(밴드 내 멤버목록에서 채팅하기 클릭 시 해당 멤버와 기존 채팅방이 있는지 검색하고 
	 * 있을시 기존 채팅방이 띄워지고 없을 시 채팅방 생성
	 */
	
	// 채팅 페이지 이동
	@RequestMapping(value = "/chatTest", method = RequestMethod.GET)
	public String chatPage() {
		return "content/chat/chatTest";
	}
}

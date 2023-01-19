package co.prjt.own.chat.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 웹소켓 서버를 사용한다는 설정. 
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	/*
	 * 클라이언트가 웹 소켓 서버에 연결하는데 사용할 웹 소켓 엔드포인트 등록 withSockJS를 통해 웹 소켓을 지원하지 않는 브라우저에 대해
	 * 웹 소켓을 대체한다. +)메소드명에 STOMP가 들어가는 경우 통신 프로토콜인 STOMP구현에서 작동된다.
	 *	STOMP가 필요 한 이유는 websocket은 통신 프로토콜이지 특정 주제에 가입한 사용자에게 메시지를 전송하는 기능을 제공하지 않는다. 
	 *	이를 쉽게 사용하기 위해 STOMP를 사용한다.
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/gs-guide-websocket").withSockJS(); // withSockJS()이용 시 웹소켓을 지원하지 않는 브라우저에서 fallback 옵션을 활성화하는데 사용됨.
	}

	/* 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅하는데 사용될 메시지 브로커 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) { 
		//configureMessageBroker 메소드는 한 클라이언트에서 다른 클라이언트로 메세지를 라우팅 할 떄 사용하는 브로커를 구성
		
		// app 경로로 시작되는 메시지만 message-handling methods로 라우팅된다. 받는것 
		registry.setApplicationDestinationPrefixes("/app");
		// /topic 으로 시작되는 주제를 가진(구독한) 모든 사용자들에게 메시지를 방송한다. 보내는것
		registry.enableSimpleBroker("/topic");
	}
}

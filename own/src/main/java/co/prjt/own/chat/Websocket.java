package co.prjt.own.chat;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/websocket")
public class Websocket {
	private static ArrayList<Session> sessionList = new ArrayList<Session>();
	
	@OnOpen
	public void handleOpen(Session session) {
		if(session != null) {
			String sessionId = session.getId();
			
			System.out.println("client is connected.sessionId == [" + sessionId + "]");
			sessionList.add(session);
			
			sendMessageToAll("*****[USER-" + sessionId + "] is connected. *****");
		}
	}
	
	@OnMessage
	public String handleMessage(String message, Session session) {
		if(session != null) {
			String sessionId = session.getId();
			System.out.println("message is arrived.sessionId == [" + sessionId + "]/message == [" + message + "]");
			sendMessageToAll("[USER-" + sessionId + "]" + message);
		}
		return null;
	}
	
	@OnClose
	public void handleClose(Session session) {
		if(session != null) {
			String sessionId = session.getId();
			System.out.println("client is disconnected.sessionId == [" + sessionId + "]");
			
			sendMessageToAll("*****[USER-" + sessionId + "]is disconnected. *****");
		}
	}
	
	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}
	
	private boolean sendMessageToAll(String message) {
		if(sessionList == null) {
			return false;
		}
		int sessionCount = sessionList.size();
		if(sessionCount < 1) {
			return false;
		}
		
		Session singleSession = null;
		for(int i=0; i<sessionCount; i++) {
			singleSession = sessionList.get(i);
			if(singleSession == null) {
				continue;
			}
			if (!singleSession.isOpen()) {
				continue;
			}
			sessionList.get(i).getAsyncRemote().sendText(message);
		}
		return true;
	}
}

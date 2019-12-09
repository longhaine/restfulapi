package com.restful.websocket;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.restful.jwt.JwtTokenProvider;

public class CustomHandShakeInterceptor implements HandshakeInterceptor{
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		try {
			String cookie = request.getHeaders().get("cookie").toString();//get cookie and this contains jsessionid and token
			int positionOfToken = cookie.indexOf("Token"); //output find positionoftoken and + 6 to substring get token
			if(positionOfToken == -1) {//there is no token in this cookie
			return false;
			}
			String token = cookie.substring(positionOfToken+6,cookie.length()-1); // token after substring
			return tokenProvider.validateToken(token);
		}catch(Exception ex) {
			System.out.println("error: "+ex.getMessage());
		}
		return false;
	}
	
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		// TODO Auto-generated method stub
		
	}
}

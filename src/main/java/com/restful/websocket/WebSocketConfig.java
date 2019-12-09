package com.restful.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	 @Override
	 public void configureMessageBroker(MessageBrokerRegistry config) {
	  config.enableSimpleBroker("/channel","/user");
	  config.setApplicationDestinationPrefixes("/app");
	 }
	 
	 
	 @Override
	 public void registerStompEndpoints(StompEndpointRegistry registry) {
	  registry.addEndpoint("/chat").addInterceptors(customHandShakeInterceptor()).setAllowedOrigins("*").withSockJS();
	 }
	 
	 @Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		 registration.interceptors(customChannelInterceptor());
	}
	 @Bean
	 public CustomChannelInterceptor customChannelInterceptor() {
		 return new CustomChannelInterceptor();
	 }
	 
	 @Bean
	 public CustomHandShakeInterceptor customHandShakeInterceptor() {
		 return new CustomHandShakeInterceptor();
	 }
}

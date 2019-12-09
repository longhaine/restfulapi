package com.restful.websocket;

import org.springframework.stereotype.Component;

@Component
public class Message {
	private String action;
	private String message;
	private String from;
	private String destination;
	public Message(String from, String destination) {
		this.from = from;
		this.destination = destination;
	}
	public Message(String action, String from, String message) {
		this.action = action;
		this.from = from;
		this.message = message;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Message() {}
	public Message(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public String getFrom() {
		return from;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	
}

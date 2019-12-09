package com.restful.websocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class CustomEventListenerWebsocket {
	
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	
	Map<String, ArrayList<Message>> subscribersInDestination = new HashMap<String,ArrayList<Message>>();
	
	@EventListener(SessionSubscribeEvent.class)
	public void onSubscribeEvent(SessionSubscribeEvent event) {
	    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
	    String destination = headerAccessor.getSubscriptionId(); // subscriptionid is destination
	    String from = headerAccessor.getFirstNativeHeader("from");
	    Message subscriber = new Message();
	    subscriber.setFrom(from);
	    subscriber.setAction("connect"); //set action when subscriber subscribing
	    headerAccessor.getSessionAttributes().put(destination,subscriber); //add subscriber to destination
	    addSubToDestination(destination, subscriber);
	    handleSendingMessage(destination,"connect",from);
	}
	@EventListener(SessionUnsubscribeEvent.class)
	public void onUnsubscribeEvent(SessionUnsubscribeEvent event) {
	    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
	    String destination = headerAccessor.getSubscriptionId(); // subscriptionid is destination
	    Message subscriber = (Message) headerAccessor.getSessionAttributes().get(destination); // get subscriber from destination
	    subscriber.setAction("disconnect"); // set action when subscriber unsubscribing
	    headerAccessor.getSessionAttributes().remove(destination); // remove subscriber from destination
	    removeSubFromDestination(destination, subscriber);
	    handleSendingMessage(destination,"disconnect",subscriber.getFrom());
	}
	@EventListener(SessionDisconnectEvent.class)
	public void onDisconnectEvent(SessionDisconnectEvent event) {
	    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
	    String destination = headerAccessor.getSubscriptionId(); // subscriptionid is destination
	    Message subscriber = (Message) headerAccessor.getSessionAttributes().get("/channel/public"); // get subscriber from destination
	    subscriber.setAction("disconnect"); // set action when subscriber unsubscribing
	    headerAccessor.getSessionAttributes().remove("/channel/public"); // remove subscriber from destination
	    removeSubFromDestination("/channel/public", subscriber);
	    handleSendingMessage("/channel/public","disconnect",subscriber.getFrom());
	}
	public void handleSendingMessage(String destination, String action, String from) {
		try {
			Thread.sleep(100);
		    Map<String, Object> message = new HashMap<String,Object>();
		    message.put("action", action);
		    message.put("from", from);
		    message.put("subscribers", getSubsByDestination(destination));
			messagingTemplate.convertAndSend(destination,message);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public ArrayList<Message> getSubsByDestination(String destination) {
	    ArrayList<Message> allSubscribers = subscribersInDestination.get(destination);// get all subs by destination
	    if(allSubscribers == null) {
	    	allSubscribers = new ArrayList<Message>();
	    }
	    return allSubscribers;
	}
	
	public void addSubToDestination(String destination, Message subscriber){
		ArrayList<Message> allSubscribers = getSubsByDestination(destination);
		allSubscribers.add(subscriber); // add subscriber
		subscribersInDestination.put(destination,allSubscribers); //replace
	}
	
	public void removeSubFromDestination(String destination, Message subscriber) {
		ArrayList<Message> allSubscribers = getSubsByDestination(destination);
		int indexOfSub = -1;
		int subsCount = allSubscribers.size();
		for(int i = 0 ; i < subsCount ; i++) {
			if(allSubscribers.get(i).getFrom().equals(subscriber.getFrom())){
				indexOfSub = i;
				break;
			}
		}
		allSubscribers.remove(indexOfSub);
	}
}

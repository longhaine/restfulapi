package com.restful.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin("http://localhost:4200")
public class WebSocketController {

    @MessageMapping("/chat.message")
    @SendTo("/channel/public")
    public Message message(Message message) throws Exception {
        return message;
    }
}

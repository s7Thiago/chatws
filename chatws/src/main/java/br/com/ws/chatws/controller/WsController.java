package br.com.ws.chatws.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import br.com.ws.chatws.model.ChatMessage;
import br.com.ws.chatws.model.ChatMessage.MessageType;
import br.com.ws.chatws.utils.StringUtils;

@Controller
public class WsController {

    @MessageMapping("/join")
    @SendTo("/topic/messages")
    public ChatMessage join(@Payload ChatMessage message, SimpMessageHeaderAccessor headers) {
        headers.getSessionAttributes().put("username", message.getSender());

        message.setType(MessageType.JOIN);
        message.setTime(StringUtils.getCurrentTimeStamp());

        return message;
    }

    @MessageMapping("/talk")
    @SendTo("/topic/messages")
    public ChatMessage send(@Payload ChatMessage chatMessage) throws Exception {
        return new ChatMessage(chatMessage.getSender(), chatMessage.getContent(), MessageType.CHAT);
    }
}
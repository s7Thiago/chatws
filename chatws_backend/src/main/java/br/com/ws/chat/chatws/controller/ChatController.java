package br.com.ws.chat.chatws.controller;

import java.util.Optional;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import br.com.ws.chat.chatws.model.ChatMessage;

@Controller
public class ChatController {

    @MessageMapping("/join")
    @SendTo("/topic/messages")
    public ChatMessage join(@Payload ChatMessage message, SimpMessageHeaderAccessor headers) {

        if (Optional.ofNullable(headers).isPresent()) {
            headers.getSessionAttributes().put("username", message.getSender());
        }

        return message;
    }

    @MessageMapping("/talk")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        return message;
    }

}

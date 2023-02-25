package br.com.ws.chatws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import br.com.ws.chatws.model.ChatMessage;
import br.com.ws.chatws.model.ChatMessage.MessageType;
import br.com.ws.chatws.service.WsService;
import br.com.ws.chatws.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WsController {

    @Autowired
    private WsService service;

    @MessageMapping("/join")
    @SendTo("/topic/messages")
    public ChatMessage join(@Payload ChatMessage message, SimpMessageHeaderAccessor headers) {
        headers.getSessionAttributes().put("username", message.getSender());

        message.setType(MessageType.JOIN);
        message.setTime(StringUtils.getCurrentTimeStamp());

        WsController.log.info("User joined: " + new Gson().toJson(message));
        service.saveLog(message);

        return message;
    }

    @MessageMapping("/talk")
    @SendTo("/topic/messages")
    public ChatMessage send(@Payload ChatMessage chatMessage) throws Exception {

        service.saveLog(chatMessage);

        WsController.log.info("New message: " + new Gson().toJson(chatMessage));

        return new ChatMessage(chatMessage.getSender(), chatMessage.getContent(), MessageType.CHAT);
    }
}
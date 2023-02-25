package br.com.ws.chatws.model;

import br.com.ws.chatws.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChatMessage {

    private String sender;
    private String content;
    private MessageType type;
    private String time;

    public ChatMessage() {
        this.time = StringUtils.getCurrentTimeStamp();
    }

    public ChatMessage(String sender, String content, MessageType type) {
        this.sender = sender;
        this.content = content;
        this.type = type;
        this.time = StringUtils.getCurrentTimeStamp();
    }

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

}
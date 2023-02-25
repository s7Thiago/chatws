package br.com.ws.chatws.model.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import br.com.ws.chatws.model.ChatMessage.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "logs", createIndex = true)
public class ElasticSearchLogModel {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String cpf;

    @Field(type = FieldType.Text)
    private String interaction;

    @Field(type = FieldType.Date, format = DateFormat.date)
    private String createdAt;

    @Field(type = FieldType.Text)
    private LogType logType;
    
    @Field(type = FieldType.Text)
    private MessageType type;

    @Field(type = FieldType.Text)
    private String user;



    public ElasticSearchLogModel(InstanceBuilder builder) {
        this.id = builder.id;
        this.cpf = builder.cpf;
        this.interaction = builder.interaction;
        this.createdAt = builder.createdAt;
        this.logType = builder.logType;
        this.type = builder.type;
        this.user = builder.user;
    }

    public enum LogType {
        ERROR, INFO, DEBUG
    }

    @Data
    @Builder
    public static class InstanceBuilder {
        private String id;
        private String cpf;
        private String interaction;
        private String createdAt;
        private LogType logType;
        private MessageType type;
        private String user;
    }
}

package br.com.ws.chatws.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.UUID;
import java.lang.Class;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.GsonBuilder;

import br.com.ws.chatws.model.ChatMessage;
import br.com.ws.chatws.model.elasticsearch.ElasticSearchLogModel;
import br.com.ws.chatws.model.elasticsearch.ElasticSearchLogModel.InstanceBuilder;
import br.com.ws.chatws.model.elasticsearch.ElasticSearchLogModel.LogType;
import br.com.ws.chatws.repository.ElasticSearchLogRepository;
import br.com.ws.chatws.utils.StringUtils;

@Service
public class WsService {
    private final ElasticSearchLogRepository esRepository;

    public WsService(ElasticSearchLogRepository repository) {
        this.esRepository = repository;
    }

    public void saveLog(ChatMessage message) {

        String url = "http://elasticsearch:9200/chat/_doc/?pretty=true";
        Class<ElasticSearchLogModel> targetClass = ElasticSearchLogModel.class;

        ElasticSearchLogModel log = new ElasticSearchLogModel(InstanceBuilder.builder()
                .logType(LogType.DEBUG)
                .type(message.getType())
                .user(message.getSender())
                .id(UUID.randomUUID().toString())
                .cpf(String.format("%011d", new Random().nextInt(999999999)))
                .interaction(new GsonBuilder().setPrettyPrinting().create().toJson(message))
                .createdAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).format(StringUtils.getCurrentDateFormat()))
                .build());

        RestTemplate template = new RestTemplate();
        HttpEntity<ElasticSearchLogModel> entity = new HttpEntity<>(log);
        ElasticSearchLogModel response = template.postForObject(url, entity, targetClass);

        // esRepository.save(log);

    }
}

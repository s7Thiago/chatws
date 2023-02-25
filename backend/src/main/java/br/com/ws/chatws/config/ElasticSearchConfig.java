package br.com.ws.chatws.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "br.com.ws.chatws.repository")
@ComponentScan(basePackages = { "br.com.ws.chatws.controller" })
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    // Host from properties
    @Value("${elasticsearch.host}")
    private String elasticsearchHost;

    // Port from properties
    @Value("${elasticsearch.port}")
    private int elasticsearchPort;

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(String.format("%s:%s", elasticsearchHost, elasticsearchPort))
                .build();

        return RestClients.create(clientConfiguration)
                .rest();
    }
}
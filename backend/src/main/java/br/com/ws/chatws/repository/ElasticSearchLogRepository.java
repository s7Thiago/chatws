package br.com.ws.chatws.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import br.com.ws.chatws.model.elasticsearch.ElasticSearchLogModel;

@Repository
public interface ElasticSearchLogRepository extends ElasticsearchRepository<ElasticSearchLogModel, String> {

}
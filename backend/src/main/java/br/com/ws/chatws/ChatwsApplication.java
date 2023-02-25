package br.com.ws.chatws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @EnableElasticsearchRepositories(basePackages = "br.com.ws.chatws.repository")
@ComponentScan(basePackages = "br.com.ws.chatws")
public class ChatwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatwsApplication.class, args);
	}

}
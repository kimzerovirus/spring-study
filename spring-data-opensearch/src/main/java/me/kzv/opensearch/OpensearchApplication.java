package me.kzv.opensearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * If you are using Spring Data OpenSearch along with Spring Boot 3.x,
 * there is a dedicated spring-data-opensearch-starter module.
 * You may consider excluding the ElasticsearchDataAutoConfiguration configuration from automatic discovery
 * (otherwise, the Elasticsearch related initialization kicks in, see please https://github.com/spring-projects/spring-boot/issues/33010).
 * */
@SpringBootApplication(exclude = ElasticsearchDataAutoConfiguration.class)
public class OpensearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpensearchApplication.class, args);
    }

}

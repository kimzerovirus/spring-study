package me.kzv.estest.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

@EnableReactiveElasticsearchRepositories // (basePackages = "me.kzv.estest.repository")
@Configuration
public class ReactiveRestClientConfig extends ReactiveElasticsearchConfiguration {

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
    }
}

/**
 * @Deprecated - 5부터 없어짐
 * @Configuration
 * public class ReactiveRestClientConfig extends AbstractReactiveElasticsearchConfiguration {
 *     @Override
 *     public ReactiveElasticsearchClient reactiveElasticsearchClient() {
 *         final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
 *                 .connectedTo("localhost:9200")
 *                 .build();
 *         return ReactiveRestClients.create(clientConfiguration);
 *     }
 * }
 */
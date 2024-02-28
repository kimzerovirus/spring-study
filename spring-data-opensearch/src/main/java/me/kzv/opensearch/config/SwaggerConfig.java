package me.kzv.opensearch.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info().title("Spring Data OpenSearch example")
                .description("Spring Data OpenSearch example with Testcontainers")
                .version("v0.0.1")
                .contact(getContactDetails()));
    }

    private Contact getContactDetails() {
        return new Contact().name("kimzerovirus")
                .email("zerovirus96@gmail.com")
                .url("https://github.com/kimzerovirus");
    }
}
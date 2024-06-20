package me.kzv.productservice.service.client;

import lombok.RequiredArgsConstructor;
import me.kzv.productservice.service.model.Category;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class CategoryRestTemplateClient {
    private final RestTemplate restTemplate;

    public Category getOrganization(String categoryUuid){
        ResponseEntity<Category> restExchange =
                restTemplate.exchange(
                        "http://localhost:8000/category-service/category/v1/{categoryUuid}",
                        HttpMethod.GET,
                        null, Category.class, categoryUuid);

        return restExchange.getBody();
    }
}

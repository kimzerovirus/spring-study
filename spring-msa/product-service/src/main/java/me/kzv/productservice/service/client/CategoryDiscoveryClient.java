package me.kzv.productservice.service.client;

import lombok.RequiredArgsConstructor;
import me.kzv.productservice.exception.InstanceNotFoundException;
import me.kzv.productservice.service.model.Category;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CategoryDiscoveryClient {
    private final DiscoveryClient discoveryClient;

    public Category getCategory(String categoryUuId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("category-service");

        if (instances.isEmpty()) throw new InstanceNotFoundException();

        String serviceUri = String.format("%s/v1/category/%s",instances.get(0).getUri().toString(), categoryUuId);

        ResponseEntity<Category> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, Category.class, categoryUuId);

        return restExchange.getBody();
    }
}

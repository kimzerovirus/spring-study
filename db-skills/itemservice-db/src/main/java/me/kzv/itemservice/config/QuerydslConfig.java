package me.kzv.itemservice.config;

import me.kzv.itemservice.repository.ItemRepository;
import me.kzv.itemservice.repository.jpa.JpaItemRepositoryV3;
import me.kzv.itemservice.service.ItemService;
import me.kzv.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class QuerydslConfig {

    private final EntityManager em;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV3(em);
    }

}

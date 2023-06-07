package me.kzv.itemservice.config;

import me.kzv.itemservice.repository.ItemRepository;
import me.kzv.itemservice.repository.jpa.JpaItemRepositoryV2;
import me.kzv.itemservice.repository.jpa.SpringDataJpaItemRepository;
import me.kzv.itemservice.service.ItemService;
import me.kzv.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringDataJpaConfig {

    private final SpringDataJpaItemRepository springDataJpaItemRepository;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV2(springDataJpaItemRepository);
    }

}

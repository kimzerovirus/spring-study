package me.kzv.itemservice.config;

import me.kzv.itemservice.repository.ItemRepository;
import me.kzv.itemservice.repository.jdbctemplate.JdbcTemplateItemRepositoryV3;
import me.kzv.itemservice.repository.mybatis.ItemMapper;
import me.kzv.itemservice.repository.mybatis.MyBatisItemRepository;
import me.kzv.itemservice.service.ItemService;
import me.kzv.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final ItemMapper itemMapper;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new MyBatisItemRepository(itemMapper);
    }

}

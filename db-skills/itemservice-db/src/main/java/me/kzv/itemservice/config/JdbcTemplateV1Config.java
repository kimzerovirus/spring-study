package me.kzv.itemservice.config;

import me.kzv.itemservice.repository.ItemRepository;
import me.kzv.itemservice.repository.jdbctemplate.JdbcTemplateItemRepositoryV1;
import me.kzv.itemservice.repository.memory.MemoryItemRepository;
import me.kzv.itemservice.service.ItemService;
import me.kzv.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateV1Config {

    private final DataSource dataSource;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JdbcTemplateItemRepositoryV1(dataSource);
    }

}

package me.kzv.itemservice.config;

import lombok.RequiredArgsConstructor;
import me.kzv.itemservice.repository.ItemRepository;
import me.kzv.itemservice.repository.jdbctemplate.JdbcTemplateItemRepositoryV2;
import me.kzv.itemservice.service.ItemService;
import me.kzv.itemservice.service.ItemServiceV1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateV2Config {
    private final DataSource dataSource;

    @Bean
    public ItemService itemService(){
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository(){
        return new JdbcTemplateItemRepositoryV2(dataSource);
    }
}

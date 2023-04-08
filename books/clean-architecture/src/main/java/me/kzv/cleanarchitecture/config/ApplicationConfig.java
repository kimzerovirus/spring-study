package me.kzv.cleanarchitecture.config;

import me.kzv.cleanarchitecture.account.application.service.MoneyTransferProperties;
import me.kzv.cleanarchitecture.account.domain.Money;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApplicationConfigProperties.class)
public class ApplicationConfig {

    /**
     * Adds a use-case-specific {@link MoneyTransferProperties} object to the application context. The properties
     * are read from the Spring-Boot-specific {@link ApplicationConfigProperties} object.
     */
    @Bean
    public MoneyTransferProperties moneyTransferProperties(ApplicationConfigProperties applicationConfigProperties) {
        return new MoneyTransferProperties(Money.of(applicationConfigProperties.getTransferThreshold()));
    }

}

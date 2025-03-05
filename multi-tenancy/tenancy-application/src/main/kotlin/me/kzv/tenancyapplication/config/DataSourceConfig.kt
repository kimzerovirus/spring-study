package me.kzv.tenancyapplication.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfig {
    @Bean
    fun defaultListableBeanFactory() = DefaultListableBeanFactory()

    @Bean
    @ConfigurationProperties("spring.database")
    fun datasourceProperties() = DataSourceProperties()

    @Bean
    @LiquibaseDataSource
    @ConfigurationProperties("spring.datasource.hikari")
    fun dataSource() = datasourceProperties()
        .initializeDataSourceBuilder()
        .type(HikariDataSource::class.java)
        .build().apply {
            poolName = "dataSource"
        }
}
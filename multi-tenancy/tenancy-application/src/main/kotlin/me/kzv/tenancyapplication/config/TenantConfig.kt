package me.kzv.tenancyapplication.config

import org.hibernate.cfg.AvailableSettings
import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TenantConfig {
    @Bean
    fun multiTenancyCustomizer(
        provider: MultiTenantConnectionProvider<String>,
        resolver: CurrentTenantIdentifierResolver<String>
    ): HibernatePropertiesCustomizer {
        // HibernatePropertiesCustomizer를 implements 하여 구현해도 됨
        return HibernatePropertiesCustomizer {
            it.apply {
                this[AvailableSettings.CONNECTION_PROVIDER] = provider
                this[AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER] = resolver
            }
        }
    }
}
package me.kzv.tenancyapplication.tenant

import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.springframework.stereotype.Component

@Component
class DefaultCurrentIdentifierResolver : CurrentTenantIdentifierResolver<String> {
    override fun resolveCurrentTenantIdentifier(): String {
        return TenantContext.getTenantName()
    }

    override fun validateExistingCurrentSessions(): Boolean {
        return true
    }
}
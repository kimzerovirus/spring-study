package me.kzv.tenancyapplication.tenant

import org.hibernate.context.spi.CurrentTenantIdentifierResolver

class DefaultCurrentIdentifierResolver (): CurrentTenantIdentifierResolver<String> {
    override fun resolveCurrentTenantIdentifier(): String {
        return TenantContext.getTenantName()
    }

    override fun validateExistingCurrentSessions(): Boolean {
        return true
    }
}
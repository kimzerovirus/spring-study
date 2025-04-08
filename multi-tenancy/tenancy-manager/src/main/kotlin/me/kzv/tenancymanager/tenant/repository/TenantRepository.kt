package me.kzv.tenancymanager.tenant.repository

import me.kzv.tenancymanager.tenant.entity.TenantEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TenantRepository : JpaRepository<TenantEntity, Long> {
}
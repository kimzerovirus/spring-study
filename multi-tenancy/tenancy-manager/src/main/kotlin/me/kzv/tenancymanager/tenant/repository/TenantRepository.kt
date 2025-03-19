package me.kzv.tenancymanager.tenant.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface TenantRepository : JpaRepository<TenantEntity, Long> {
}
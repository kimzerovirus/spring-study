package me.kzv.tenancymanager.repository

import me.kzv.tenancymanager.entity.Tenant
import org.springframework.data.jpa.repository.JpaRepository

interface TenantRepository : JpaRepository<Tenant, Long> {
}
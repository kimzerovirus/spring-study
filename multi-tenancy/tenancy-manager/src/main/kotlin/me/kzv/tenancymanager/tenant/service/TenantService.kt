package me.kzv.tenancymanager.tenant.service

import me.kzv.tenancymanager.tenant.repository.TenantRepository
import org.springframework.stereotype.Service

@Service
class TenantService (
    private val tenantRepository: TenantRepository,
){

}
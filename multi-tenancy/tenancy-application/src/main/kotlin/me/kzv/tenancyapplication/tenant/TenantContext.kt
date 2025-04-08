package me.kzv.tenancyapplication.tenant

import me.kzv.common.exception.TenantNotFoundException

object TenantContext {
    private val contextHolder = InheritableThreadLocal<String>()

    fun setTenantName(tenantName: String) {
        contextHolder.set(tenantName)
    }

    fun getTenantName() : String {
      return contextHolder.get() ?: throw TenantNotFoundException()
    }

    fun clear() {
        contextHolder.remove()
    }
}
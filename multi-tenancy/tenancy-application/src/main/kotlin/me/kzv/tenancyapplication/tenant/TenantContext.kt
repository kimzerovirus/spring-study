package me.kzv.tenancyapplication.tenant

object TenantContext {
    private val contextHolder = InheritableThreadLocal<String>()

    fun setTenantName(tenantName: String) {
        contextHolder.set(tenantName)
    }

    fun getTenantName() = contextHolder.get() ?: "default_tenant"

    fun clear() {
        contextHolder.remove()
    }
}
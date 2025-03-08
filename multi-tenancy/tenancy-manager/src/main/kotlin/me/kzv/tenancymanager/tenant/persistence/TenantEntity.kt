package me.kzv.tenancymanager.tenant.persistence

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import me.kzv.common.jpa.BaseEntity
import me.kzv.tenancymanager.database.persistence.DatabaseEntity

@Entity
class TenantEntity (
    var name: String,
    var schemaName: String,
    var userName: String,
    var password: String,
    @ManyToOne(fetch = FetchType.LAZY)
    var database: DatabaseEntity,
) : BaseEntity() {
}
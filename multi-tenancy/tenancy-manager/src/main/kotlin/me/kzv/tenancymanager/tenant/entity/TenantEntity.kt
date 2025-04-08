package me.kzv.tenancymanager.tenant.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import me.kzv.common.jpa.BaseEntity
import me.kzv.tenancymanager.database.entity.DatabaseEntity

@Entity
class TenantEntity (
    var name: String,
    var schemaName: String,
    var userName: String,
    var password: String,
    @Column(length = 2000)
    var ddlLog: String,
    @ManyToOne(fetch = FetchType.LAZY)
    var database: DatabaseEntity,
) : BaseEntity() {
}
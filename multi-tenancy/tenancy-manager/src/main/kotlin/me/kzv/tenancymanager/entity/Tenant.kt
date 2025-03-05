package me.kzv.tenancymanager.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

@Entity
class Tenant (
    var name: String,
    var schemaName: String,
    var userName: String,
    var password: String,
    @ManyToOne(fetch = FetchType.LAZY)
    var database: Database,
) : BaseEntity() {
}
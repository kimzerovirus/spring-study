package me.kzv.tenancymanager.entity

import jakarta.persistence.Entity

@Entity
class Database (
    var name: String,
    var type: DatabaseType,
    var host: String,
    var port: Int,
) : BaseEntity() {
    fun getJdbcUrl(): String {
        return "jdbc:${type.str}://$host:$port"
    }
}
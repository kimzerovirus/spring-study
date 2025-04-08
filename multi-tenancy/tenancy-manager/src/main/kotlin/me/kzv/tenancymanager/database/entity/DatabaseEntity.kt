package me.kzv.tenancymanager.database.entity

import jakarta.persistence.Entity
import me.kzv.common.jpa.BaseEntity
import me.kzv.common.enums.DatabaseType

@Entity
class DatabaseEntity (
    var name: String,
    var type: DatabaseType,
    var host: String,
    var port: Int,
    var username: String,
    var password: String,
) : BaseEntity() {
    fun getJdbcUrl(): String {
        return "jdbc:${type.dbName}://$host:$port"
    }
}
package me.kzv.tenancymanager.database.persistence

import jakarta.persistence.Entity
import me.kzv.common.jpa.BaseEntity
import me.kzv.tenancymanager.database.DatabaseType

@Entity
class DatabaseEntity (
    var name: String,
    var type: DatabaseType,
    var host: String,
    var port: Int,
) : BaseEntity() {
    fun getJdbcUrl(): String {
        return "jdbc:${type.str}://$host:$port"
    }
}
package me.kzv.tenancymanager.tenant.repository

import com.zaxxer.hikari.HikariDataSource
import me.kzv.tenancymanager.database.entity.DatabaseEntity
import org.springframework.beans.factory.annotation.Value
import javax.sql.DataSource

abstract class AbstractDDLRepository (
    @Value("\${multi-tenancy.ddl-path}") private var ddlPath: String,
){
    fun createTenant(database: DatabaseEntity): Boolean {
        createDataSource(database)
        return true
    }

    private fun createDataSource(database: DatabaseEntity): DataSource {
        return HikariDataSource().apply {
            this.jdbcUrl = database.getJdbcUrl()
            this.username = ""
            this.password = ""
            this.driverClassName = database.type.driverClassName
        }
    }
}
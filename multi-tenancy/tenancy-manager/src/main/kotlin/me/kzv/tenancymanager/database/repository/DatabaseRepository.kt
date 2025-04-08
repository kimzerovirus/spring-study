package me.kzv.tenancymanager.database.repository

import me.kzv.tenancymanager.database.entity.DatabaseEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DatabaseRepository : JpaRepository<DatabaseEntity, Long> {
}
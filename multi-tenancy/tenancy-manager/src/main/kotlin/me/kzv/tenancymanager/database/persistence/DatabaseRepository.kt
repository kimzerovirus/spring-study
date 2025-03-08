package me.kzv.tenancymanager.database.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface DatabaseRepository : JpaRepository<DatabaseEntity, Long> {
}
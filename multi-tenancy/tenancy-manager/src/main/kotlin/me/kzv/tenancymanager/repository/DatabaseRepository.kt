package me.kzv.tenancymanager.repository

import me.kzv.tenancymanager.entity.Database
import org.springframework.data.jpa.repository.JpaRepository

interface DatabaseRepository : JpaRepository<Database, Long> {
}
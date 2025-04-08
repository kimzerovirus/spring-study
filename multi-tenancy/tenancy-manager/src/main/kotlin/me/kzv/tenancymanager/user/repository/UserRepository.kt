package me.kzv.tenancymanager.user.repository

import me.kzv.tenancymanager.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {

}
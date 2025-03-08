package me.kzv.tenancymanager.user.persistence

import jakarta.persistence.Entity
import jakarta.persistence.Table
import me.kzv.common.jpa.BaseEntity

@Table(name = "users")
@Entity
class UserEntity(
    val email: String,
    var name: String,
    var password: String,
) : BaseEntity() {

}
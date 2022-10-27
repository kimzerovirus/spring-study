package me.kzv.shop.entity

import me.kzv.shop.entity.constant.Role
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.*

@Entity
class Member(

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String,

    var email: String,

    var password: String,

    var address: String,

    @Enumerated(EnumType.STRING)
    var role:Role,

) : BaseEntity()

fun createMember(passwordEncoder: PasswordEncoder) = Member(password = passwordEncoder.encode())

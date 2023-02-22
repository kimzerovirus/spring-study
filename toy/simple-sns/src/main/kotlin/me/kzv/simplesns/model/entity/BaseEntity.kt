package me.kzv.simplesns.model.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @CreatedDate
    @Column(updatable = false)
    var registerAt: LocalDateTime? = null,

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null,

) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
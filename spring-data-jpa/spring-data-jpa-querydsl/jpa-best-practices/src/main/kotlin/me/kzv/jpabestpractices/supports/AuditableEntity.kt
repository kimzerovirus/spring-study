package me.kzv.jpabestpractices.supports

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditableEntity : BaseEntity() {
    @CreatedBy
    @Column(updatable = false)
    var createBy: String? = null
        protected set

    @CreatedDate
    @Column(updatable = false)
    var createDt = LocalDateTime.now()
        protected set

    @LastModifiedBy
    var modifyBy: String? = null
        protected set

    @LastModifiedDate
    var modifyDt = LocalDateTime.now()
        protected set
}

package me.kzv.simplesns.model.entity

import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.Instant
import java.time.LocalDateTime


@Entity
@Table(name = "\"comment\"")
@SQLDelete(sql = "UPDATE \"comment\" SET removed_at = NOW() WHERE id=?")
@Where(clause = "removed_at is NULL")
class CommentEntity(
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "post_id")
    val post: PostEntity,

    val comment: String,

    @Column(name = "removed_at")
    val removedAt: LocalDateTime? = null,
) : BaseEntity()
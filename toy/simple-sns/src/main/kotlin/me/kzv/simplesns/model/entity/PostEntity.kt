package me.kzv.simplesns.model.entity

import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.Instant
import java.time.LocalDateTime


@Entity
@Table(name = "\"post\"")
@SQLDelete(sql = "UPDATE \"post\" SET removed_at = NOW() WHERE id=?")
@Where(clause = "removed_at is NULL")
class PostEntity(
    @Column(name = "title")
    val title: String,

    @Column(name = "body", columnDefinition = "TEXT")
    val body: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    val comments: List<CommentEntity>,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    val likes: MutableList<LikeEntity> = mutableListOf(),

    @Column(name = "removed_at")
    val removedAt: LocalDateTime? = null,
) {

}
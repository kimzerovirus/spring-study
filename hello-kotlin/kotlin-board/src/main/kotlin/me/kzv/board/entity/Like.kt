package me.kzv.board.entity

import me.kzv.board.entity.enums.LikeType
import javax.persistence.*

@Entity
class Like private constructor(id: Long?, likeType: LikeType, userId: Long, postId: Long) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    var id: Long? = id
        private set

    var likeType: LikeType = likeType
        private set

    var userId: Long = userId
        private set

    var postId: Long = postId
        private set

    constructor(likeType: LikeType, userId: Long, postId: Long) : this(null, likeType, userId, postId)

    fun isLike() = likeType.isLike()
}
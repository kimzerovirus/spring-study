package me.kzv.board.entity

import javax.persistence.*

@Entity
class Comment private constructor(id: Long?, content: String, writer: String, userId: Long, postId: Long) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    var id: Long? = id
        private set

    var content: String = content
        private set

    var writer: String = writer
        private set

    var userId: Long = userId
        private set

    var postId: Long = postId
        private set

    constructor(content: String, writer: String, userId: Long, postId: Long) : this(null, content, writer, userId, postId)
}
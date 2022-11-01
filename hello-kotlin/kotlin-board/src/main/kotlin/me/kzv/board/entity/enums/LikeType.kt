package me.kzv.board.entity.enums

enum class LikeType {
    LIKE {
        override fun isLike() = true
    },
    DISLIKE {
        override fun isLike() = false
    };
    abstract fun isLike(): Boolean
}
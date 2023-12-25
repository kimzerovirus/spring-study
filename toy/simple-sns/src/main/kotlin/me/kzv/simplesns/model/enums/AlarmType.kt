package me.kzv.simplesns.model.enums

enum class AlarmType(
    val alarmText: String? = null
) {
    NEW_COMMENT_ON_POST("new comment!"),
    NEW_LIKE_ON_POST("new like!");
}

package me.kzv.simplesns.model

import me.kzv.simplesns.model.enums.AlarmType

class AlarmEvent(
    val type: AlarmType,
    val fromUserId: Long,
    val targetId: Long,
    val receiverUserId: Long = 1,
) 
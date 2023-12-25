package me.kzv.simplesns.model

import me.kzv.simplesns.model.entity.AlarmEntity

import me.kzv.simplesns.model.enums.AlarmType
import java.time.LocalDateTime


class Alarm(
    val id: Long? = null,
    val alarmType: AlarmType,
    val fromUserId: Long,
    val targetId: Long,
    val registeredAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val removedAt: LocalDateTime?,
) {
    val alarmText: String?
        get() = alarmType.alarmText

    companion object {
        fun fromEntity(entity: AlarmEntity): Alarm {
            return Alarm(
                id = entity.id,
                alarmType = entity.alarmType,
                fromUserId = entity.fromUserId,
                targetId = entity.targetId,
                registeredAt = entity.registeredAt,
                updatedAt = entity.updatedAt,
                removedAt = entity.removedAt,
            )
        }
    }
}
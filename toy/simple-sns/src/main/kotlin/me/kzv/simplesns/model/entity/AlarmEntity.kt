package me.kzv.simplesns.model.entity

import jakarta.persistence.*
import me.kzv.simplesns.model.enums.AlarmType
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDateTime


@Entity
@Table(name = "\"alarm\"", indexes = [Index(name = "user_id_idx", columnList = "user_id")])
@SQLDelete(sql = "UPDATE \"alarm\" SET removed_at = NOW() WHERE id=?")
@Where(clause = "removed_at is NULL")
class AlarmEntity(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @Enumerated(EnumType.STRING)
    val alarmType: AlarmType,

    val fromUserId: Long,

    val targetId: Long,

    val removedAt: LocalDateTime? = null,
) : BaseEntity() {


}


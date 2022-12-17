package me.kzv.kotlinjpaquerydsl.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
class Delivery(
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    val id: Long? = null,

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    var order: Order? = null,

    @Embedded
    var address: Address,

    @Enumerated(EnumType.STRING) // ordinal 은 숫자라서 나중에 테이블 변경이 있다면 숫자가 밀려서 기존데이터와의 혼동이 올 수 있다.
    var status: DeliveryStatus, // [READY, COMP]
)

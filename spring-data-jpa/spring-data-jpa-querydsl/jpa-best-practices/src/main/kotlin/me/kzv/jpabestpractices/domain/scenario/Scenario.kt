package me.kzv.jpabestpractices.domain.scenario

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import me.kzv.jpabestpractices.supports.AuditableEntity

@MappedSuperclass // 필드 정보만 상속해서 넘기므로 직접 생성할 일이 없는 추상 클래스를 권장하지만 지금 상황에서는 생성 해야하므로..
class Scenario(
    var content: String,
    val type: ScenarioType,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) //: AuditableEntity()
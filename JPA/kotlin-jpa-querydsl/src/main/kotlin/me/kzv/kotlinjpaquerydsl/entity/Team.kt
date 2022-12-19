package me.kzv.kotlinjpaquerydsl.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Team(
    @Id @GeneratedValue
    val id: Long? = null,
    var name: String,

    @OneToMany(mappedBy = "team") // 주인이 아니므로 업데이트 하지 않음
    val members: MutableList<Member> = mutableListOf()
){

    override fun toString(): String {
        return "Team(id=$id, name='$name')"
    }
}

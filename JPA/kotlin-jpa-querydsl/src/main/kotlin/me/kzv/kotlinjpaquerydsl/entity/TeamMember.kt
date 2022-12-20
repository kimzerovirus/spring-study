package me.kzv.kotlinjpaquerydsl.entity

import jakarta.persistence.*

@Entity
class TeamMember(

    @Id
    @GeneratedValue
    val id: Long? = null,

    var name: String,
    var age: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id") // 연관관계의 주인
    var team: Team,
) {

    fun changeTeam(team: Team) {
        this.team = team
        team.members.add(this)
    }

    // 양방향 연관관계로 설정된것들은 가져오면 서로 참조해서 안됨
    override fun toString(): String {
        return "Member(id=$id, name='$name')"
    }

}
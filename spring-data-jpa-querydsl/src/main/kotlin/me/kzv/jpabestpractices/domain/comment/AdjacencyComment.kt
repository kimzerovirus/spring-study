package me.kzv.jpabestpractices.domain.comment

import jakarta.persistence.*
import me.kzv.jpabestpractices.supports.AuditableEntity

/**
 * 인접 리스트
 *
 * 계층형 구조 중 가장 흔한 구조로
 * 오라클에선 주로 CONNECT BY 계층형 쿼리를 사용,
 * 대부분의 DB는 WITH RECURSIVE 재귀형 쿼리를 사용
 *
 * 팀 아래 하위 팀이 계속 있는 구조로
 * 같은 테이블을 fk로 참조 하고 있다.
 */
@Entity
class AdjacencyComment (
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id", foreignKey = ForeignKey(name = "fk_parent_comment_id"))
    var parent: AdjacencyComment? = null,

    @OneToMany(
        mappedBy = "parent",
        cascade = [CascadeType.REMOVE],
        orphanRemoval = true,
        targetEntity = AdjacencyComment::class,
    )
    var commentList: List<AdjacencyComment> = mutableListOf(),

    @Column
    var text: String
) : AuditableEntity()
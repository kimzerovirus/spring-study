package me.kzv.concurrencycontrol.node

import jakarta.persistence.*

@Table(uniqueConstraints = [UniqueConstraint(name = "uq_node", columnNames = ["node_id", "node_type"])]) // 복합키 처럼 여러 열을 합쳐서 유니크 걸 수 있음
@Entity
class Node(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "node_id")
    val id: Long? = null,

//    @Column(name = "node_name", unique = true) // 열 유니크 제약 조건
    @Column(name = "node_name")
    var name: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "node_type", nullable = false)
    val nodeType: NodeType,
) {
    fun updateName(name: String) {
        this.name = name
    }
}
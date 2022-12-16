package me.kzv.kotlinjpaquerydsl.entity

import jakarta.persistence.*
import me.kzv.kotlinjpaquerydsl.entity.items.Item

@Entity
class Category(
    @Id @GeneratedValue
    @Column(name = "category_id")
    val id: Long? = null,

    val name: String,

    @ManyToMany // 관계형 db 는 다대다 관계가 바로 안되고 다대일 일대다로 중간 테이블을 통하여 조인 되어야 한다. 하지만 ManyToMany는 중간 테이블에 컬럼을 추가할 수 없고, 세밀하게 쿼리를 실행하기에 적합하지 않으므로 실무사용을 지양한다.  ManyToOne -> OneToMany 로 풀어내자
    @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    val items: MutableList<Item> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category,

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.ALL])
    val child: MutableList<Category> = mutableListOf()
){
    fun addChildCategory(child: Category){
        this.child.add(child)
        child.parent = this
    }
}
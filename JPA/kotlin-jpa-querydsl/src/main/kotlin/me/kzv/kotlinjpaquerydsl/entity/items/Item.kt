package me.kzv.kotlinjpaquerydsl.entity.items

import jakarta.persistence.*
import me.kzv.kotlinjpaquerydsl.entity.Category
import me.kzv.kotlinjpaquerydsl.exception.NotEnoughStockException

/**
 * 참고
 * https://devroach.tistory.com/49
 * 정규화가 이루어지지 않은 하나의 테이블이지만 join 등이 없으므로 성능상의 이점을 가질 수 있다.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 하나의 테이블로 생성해주는 전략
@DiscriminatorColumn(name = "dtype") //부모 클래스에 선언한며 하위 클래스를 구분하는 용도의 컬럼 [default = DTYPE]
abstract class Item() {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    val id: Long? = null
    var name: String = ""
    var price: Int = 0
    var stockQuantity: Int = 0

    @ManyToMany(mappedBy = "items") // 실무에서 쓰면 복잡해서 문제를 야기할 수 있음, 따라서 안씀
    val categories: MutableList<Category> = mutableListOf()

    constructor(name: String, price: Int, stockQuantity: Int) : this() {
        this.name = name
        this.price = price
        this.stockQuantity = stockQuantity
    }

    //==비즈니스 로직==//
    /**
     * 재고 수량 증가
     */
    fun removeStock(stockQuantity: Int) {
        this.stockQuantity += stockQuantity
    }

    fun addStock(stockQuantity: Int) {
        val restStock = this.stockQuantity - stockQuantity
        if (restStock < 0) {
            throw NotEnoughStockException("need more stock")
        }

        this.stockQuantity = restStock
    }
}

/*
    SINGLE_TABLE : 하나의 테이블로 생성해주는 전략
    TABLE_PER_CLASS : 상속받은 클래스 하나하나를 다른 테이블로 생성하는 전략
 */
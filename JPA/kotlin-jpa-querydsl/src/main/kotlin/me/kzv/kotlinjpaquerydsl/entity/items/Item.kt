package me.kzv.kotlinjpaquerydsl.entity.items

import jakarta.persistence.*
import me.kzv.kotlinjpaquerydsl.annotation.AllOpen
import me.kzv.kotlinjpaquerydsl.entity.Category
import me.kzv.kotlinjpaquerydsl.exception.NotEnoughStockException

/**
 * 참고
 * https://devroach.tistory.com/49
 * 정규화가 이루어지지 않은 하나의 테이블이지만 join 등이 없으므로 성능상의 이점을 가질 수 있다.
 *
 * 코틀린의 추상클래스 getter 는 final 로 생성 되는 이슈가 있다. -> allopen
 * https://v3.leedo.me/devs/81
 * https://kotlinlang.org/docs/all-open-plugin.html#gradle
 */
@AllOpen
@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // 각각의 테이블로 생성해주는 전략
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 하나의 테이블로 생성해주는 전략
@DiscriminatorColumn(name = "dtype") //부모 클래스에 선언한며 하위 클래스를 구분하는 용도의 컬럼 [default = DTYPE]
abstract class Item() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    var id: Long? = null
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
        val restStock = this.stockQuantity - stockQuantity
        if (restStock < 0) {
            throw NotEnoughStockException("재고 수량이 부족합니다")
        }

        this.stockQuantity = restStock
    }

    fun addStock(stockQuantity: Int) {
        this.stockQuantity += stockQuantity
    }
}

/*
    SINGLE_TABLE : 하나의 테이블로 생성해주는 전략
    TABLE_PER_CLASS : 상속받은 클래스 하나하나를 다른 테이블로 생성하는 전략
 */
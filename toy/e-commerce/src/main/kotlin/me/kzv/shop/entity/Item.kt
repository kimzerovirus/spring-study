package me.kzv.shop.entity

import me.kzv.shop.entity.constant.ItemSellStatus
import me.kzv.shop.exception.OutOfStockException
import javax.persistence.*

@Entity
class Item(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    var id: Long? = null,

    var itemName: String, // 상품명

    var price: Int, // 상품 가격
    // 포인트나 할인제도가 추가 될경우 ...

    var stockNumber: Int, // 재고 수량

    @Lob
    var itemDetail: String, // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    var itemSellStatus: ItemSellStatus, //판매 상태

) : BaseEntity() {
    fun updateItem() {

    }

    fun removeStock(stockNumber: Int) {
        val restStock = this.stockNumber - stockNumber
        if (restStock < 0) {
            throw OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량: ${this.stockNumber})")
        }

        this.stockNumber = restStock
    }

    fun addStock(stockNumber: Int){
        this.stockNumber = stockNumber
    }
}
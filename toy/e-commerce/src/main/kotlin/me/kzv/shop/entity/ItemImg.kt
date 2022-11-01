package me.kzv.shop.entity

import javax.persistence.*

@Entity
class ItemImg (
    @Id
    @Column(name = "item_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var imgName: String,

    var originImgName: String,

    var imgUrl: String,

    var mainImgYn: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

) : BaseEntity() {

    fun updateItemImg(originImgName: String, imgName: String, imgUrl: String) {
        this.originImgName = originImgName
        this.imgName = imgName
        this.imgUrl = imgUrl
    }
}
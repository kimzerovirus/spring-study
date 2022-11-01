package me.kzv.shop.controller.dto

import com.querydsl.core.annotations.QueryProjection
import me.kzv.shop.entity.Item
import me.kzv.shop.entity.constant.ItemSellStatus
import org.modelmapper.ModelMapper
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


data class ItemDto(
    val id: Long,
    val itemNm: String,
    val price: Int,
    val itemDetail: String,
    val sellStatCd: String,
    val regTime: LocalDateTime,
    val updateTime: LocalDateTime,
)

data class ItemFormDto(
    val id: Long,

    @field:NotBlank(message = "상품명은 필수 입력 값입니다.")
    val itemNm: String,

    @field:NotNull(message = "가격은 필수 입력 값입니다.")
    val price: Int,

    @field:NotBlank(message = "상품 상세는 필수 입력값입니다.")
    val itemDetail: String,

    @field:NotNull(message = "재고는 필수 입력 값입니다.")
    val stockNumber: Int,

    val itemSellStatus: ItemSellStatus,

    val itemImgDtoList: List<ItemImgDto> = mutableListOf(),

    val itemImgIds: List<Long> = mutableListOf(),
) {
//    companion object {
//        val modelMapper: ModelMapper = ModelMapper()
//
//        fun of(item: Item): ItemFormDto = modelMapper.map(item, ItemFormDto::class.java)
//    }
//
//    fun createItem(): Item = modelMapper.map(this, Item::class.java)
}

// ModelMapper 는 Reflection API 를 사용하고 Reflection 은 생성자의 인자 정보를 가지고 객체를 생성한다.
// 하지만 dataClass 에 기본생성자가 존재하지 않아서 Runtime 때 Reflection 이 인자 정보를 가지고 오지 못하므로 에러가 발생한다.

data class ItemImgDto(
     val id: Long,
     val imgName: String,
     val oriImgName: String,
     val imgUrl: String,
     val repImgYn: String,
)

class ItemSearchDto (
     val searchDateType: String,
     val searchSellStatus: ItemSellStatus,
     val searchBy: String,
     val searchQuery: String = "",
)

class MainItemDto @QueryProjection constructor(
     val id: Long,
     val itemNm: String,
     val itemDetail: String,
     val imgUrl: String,
     val price: Int
)
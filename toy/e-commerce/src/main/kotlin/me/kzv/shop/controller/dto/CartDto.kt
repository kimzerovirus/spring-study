package me.kzv.shop.controller.dto

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull


data class CartDetailDto(
    val cartItemId: Long, // 장바구니 상품 아이디
    val itemNm: String, // 상품명
    val price: Int, // 상품 금액
    val count: Int, // 수량
    val imgUrl: String, // 상품 이미지 경로
)

data class CartItemDto(
    // 코틀린에서 범위를 지정하지 않고 validation 어노테이션을 사용하면 생성자의 파라미터에 설정된다.
    // 따라서 필드로 범위를 제한해야 한다.
    @field:NotNull(message = "상품 아이디는 필수 입력 값 입니다.")
    val itemId: Long,

    @field:Min(value = 1, message = "최소 1개 이상 담아주세요.")
    val count: Int,
)

data class CartOrderDto(
    val cartItemId: Long,
    val cartOrderDtoList: MutableList<CartOrderDto>
)
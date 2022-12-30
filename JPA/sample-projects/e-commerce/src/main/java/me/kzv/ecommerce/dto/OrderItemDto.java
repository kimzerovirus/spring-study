package me.kzv.ecommerce.dto;

import lombok.Data;
import me.kzv.ecommerce.entity.OrderItem;

@Data
public class OrderItemDto {
    private String itemNm;
    private int count;
    private int orderPrice;
    private String imgUrl;

    public OrderItemDto(OrderItem orderItem, String imgUrl) {
        itemNm = orderItem.getItem().getItemNm();
        count = orderItem.getCount();
        orderPrice = orderItem.getOrderPrice();
        this.imgUrl = imgUrl;
    }
}

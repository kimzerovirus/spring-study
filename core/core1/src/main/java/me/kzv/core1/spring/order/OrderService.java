package me.kzv.core1.spring.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}

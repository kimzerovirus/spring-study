package me.kzv.core1.old.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
package me.kzv.ecommercejpatdd.payment.application.port;

import me.kzv.ecommercejpatdd.order.domain.Order;
import me.kzv.ecommercejpatdd.payment.domain.Payment;

public interface PaymentPort {
    Order getOrder(Long orderId);

    void pay(int totalPrice, String cardNumber);

    void save(Payment payment);
}

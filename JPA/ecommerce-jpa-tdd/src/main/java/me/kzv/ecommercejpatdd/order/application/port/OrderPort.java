package me.kzv.ecommercejpatdd.order.application.port;

import me.kzv.ecommercejpatdd.order.domain.Order;
import me.kzv.ecommercejpatdd.product.domain.Product;

public interface OrderPort {
    Product getProductById(final Long productId);

    void save(final Order order);
}

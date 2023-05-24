package me.kzv.ecommercejpatdd.order.adapter;

import me.kzv.ecommercejpatdd.order.application.port.OrderPort;
import me.kzv.ecommercejpatdd.order.domain.Order;
import me.kzv.ecommercejpatdd.product.adapter.ProductRepository;
import me.kzv.ecommercejpatdd.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
class OrderAdapter implements OrderPort {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    private OrderAdapter(final ProductRepository productRepository, final OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Product getProductById(final Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }

    @Override
    public void save(final Order order) {
        orderRepository.save(order);
    }
}
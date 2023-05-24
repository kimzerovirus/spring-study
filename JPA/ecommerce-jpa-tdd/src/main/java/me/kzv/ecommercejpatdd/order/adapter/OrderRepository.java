package me.kzv.ecommercejpatdd.order.adapter;

import me.kzv.ecommercejpatdd.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
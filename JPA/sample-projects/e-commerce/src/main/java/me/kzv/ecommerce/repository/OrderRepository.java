package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.entity.Item;
import me.kzv.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

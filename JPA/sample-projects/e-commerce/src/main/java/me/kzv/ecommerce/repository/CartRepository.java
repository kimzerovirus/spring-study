package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

interface CartRepository extends JpaRepository<Cart, Long> {
}

package me.kzv.ecommercejpatdd.product.adapter;

import me.kzv.ecommercejpatdd.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

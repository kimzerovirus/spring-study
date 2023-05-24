package me.kzv.ecommercejpatdd.product.application.port;

import me.kzv.ecommercejpatdd.product.domain.Product;

public interface ProductPort {
    void save(final Product product);

    Product getProduct(Long productId);
}
package me.kzv.productservice.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class ProductRepository {
    private static final Map<String, Product> db = new HashMap<>();

    static {
        List<String> productNames = List.of("수박", "바지", "책");
        productNames.forEach(name -> {
            var product = Product.create(name,name + "입니다.", null);
            db.put(product.getUuid(), product);
        });

        db.values().forEach(product -> log.info(product.toString()));
    }

    public Optional<Product> findById(String uuid){
        return Optional.of(db.get(uuid));
    }

    public List<Product> findAll() {
        return db.values().stream().toList();
    }

    public Product save(Product product){
        return db.put(product.getUuid(), product);
    }

    public Product delete(String uuid) {
        return db.put(uuid, null);
    }
}

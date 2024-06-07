package me.kzv.productservice.service;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import me.kzv.productservice.domain.Product;
import me.kzv.productservice.domain.ProductRepository;
import me.kzv.productservice.dto.ProductCreateDto;
import me.kzv.productservice.dto.ProductUpdateDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(String uuid) {
        return productRepository.findById(uuid).orElseThrow(NotFoundException::new);
    }

    public Product create(ProductCreateDto dto){
        return productRepository.save(dto.toNewProduct());
    }

    public Product update(ProductUpdateDto dto) {
        Product product = this.findById(dto.getUuid());
        Product updatedProduct = product.update(dto.getName(), dto.getDescription(), dto.getCategoryId());
        return productRepository.save(updatedProduct);
    }

    public void delete(String uuid) {
        productRepository.delete(uuid);
    }
}

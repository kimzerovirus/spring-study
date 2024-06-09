package me.kzv.productservice.service;

import lombok.RequiredArgsConstructor;
import me.kzv.productservice.domain.Product;
import me.kzv.productservice.domain.ProductRepository;
import me.kzv.productservice.dto.ProductCreateDto;
import me.kzv.productservice.dto.ProductUpdateDto;
import me.kzv.productservice.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Product findById(String uuid) {
        return productRepository.findByUuid(uuid).orElseThrow(NotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Transactional
    public Product create(ProductCreateDto dto){
        return productRepository.save(dto.toNewProduct());
    }

    @Transactional
    public Product update(ProductUpdateDto dto) {
        Product product = this.findById(dto.getUuid());
        Product updatedProduct = product.update(dto.getName(), dto.getDescription(), dto.getCategoryId());
        return productRepository.save(updatedProduct);
    }

    @Transactional
    public void delete(String uuid) {
        Product product = findById(uuid);
        productRepository.delete(product);
    }
}

package me.kzv.productservice.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.productservice.domain.Product;
import me.kzv.productservice.dto.ProductResponseDto;
import me.kzv.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable String uuid) {
        Product product = productService.findById(uuid);
        return ResponseEntity.ok(ProductResponseDto.from(product));
    }
}

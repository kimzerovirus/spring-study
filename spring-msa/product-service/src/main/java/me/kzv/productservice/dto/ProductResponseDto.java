package me.kzv.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kzv.productservice.domain.Product;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductResponseDto {
    private String uuid;
    private String name;
    private String description;
    private String categoryId;

    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(
                product.getUuid(),
                product.getName(),
                product.getDescription(),
                product.getCategoryId()
        );
    }
}

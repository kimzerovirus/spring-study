package me.kzv.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.kzv.productservice.domain.Product;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductResponse {
    private String uuid;
    private String name;
    private String description;
    private String categoryId;

    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getUuid(),
                product.getName(),
                product.getDescription(),
                product.getCategoryId()
        );
    }
}

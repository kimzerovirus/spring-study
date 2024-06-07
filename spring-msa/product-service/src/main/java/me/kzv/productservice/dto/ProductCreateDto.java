package me.kzv.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kzv.productservice.domain.Product;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductCreateDto {
    private String name;
    private String description;
    private String categoryId;

    public Product toNewProduct(){
        return Product.create(name, description, categoryId);
    }
}

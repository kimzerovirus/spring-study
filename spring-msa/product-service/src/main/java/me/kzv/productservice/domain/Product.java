package me.kzv.productservice.domain;

import lombok.*;

import java.util.UUID;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String uuid;
    private String name;
    private String description;
    private String categoryId;

    public static Product create(String name, String description, String categoryId){
        return Product.builder()
                .uuid(UUID.randomUUID().toString())
                .name(name)
                .description(description)
                .categoryId(categoryId)
                .build();
    }

    public Product update(String name, String description, String categoryId) {
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;

        return this;
    }
}

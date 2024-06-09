package me.kzv.productservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

package me.kzv.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductUpdateDto {
    private String uuid;
    private String name;
    private String description;
    private String categoryId;
}

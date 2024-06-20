package me.kzv.categoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private String uuid;
    private String name;
    private String description;

    public static CategoryResponse getCategory(String uuid){
        return new CategoryResponse(uuid, "식품", "신선한 상품입니다.");
    }

    public static CategoryResponse create(){
        return new CategoryResponse(UUID.randomUUID().toString(), "식품", "신선한 상품입니다.");
    }
}

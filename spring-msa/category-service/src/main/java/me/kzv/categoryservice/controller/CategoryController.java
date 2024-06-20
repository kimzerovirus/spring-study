package me.kzv.categoryservice.controller;

import me.kzv.categoryservice.dto.CategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/category")
@RestController
public class CategoryController {
    @GetMapping("/{uuid}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable String uuid){
        return ResponseEntity.ok(CategoryResponse.getCategory(uuid));
    }
}

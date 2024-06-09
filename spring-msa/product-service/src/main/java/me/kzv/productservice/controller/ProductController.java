package me.kzv.productservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.productservice.domain.Product;
import me.kzv.productservice.dto.ApiResponseDto;
import me.kzv.productservice.dto.ProductCreateDto;
import me.kzv.productservice.dto.ProductResponse;
import me.kzv.productservice.dto.ProductUpdateDto;
import me.kzv.productservice.service.ProductService;
import me.kzv.productservice.utils.ApiResponseUtils;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product")
public class ProductController {
    private final ProductService productService;
    private final MessageSource messageSource;

    @GetMapping("/{uuid}")
    public ApiResponseDto<ProductResponse> getProduct(@PathVariable String uuid) {
        Product product = productService.findById(uuid);
        ProductResponse response = ProductResponse.from(product);
        log.info(response.toString());
        return ApiResponseUtils.ok(response)
                .add( // RESPONSE에 HETOAS를 이용하여 링크를 생성해준다.
                        linkTo(methodOn(ProductController.class).getProduct(uuid)).withSelfRel(),
                        linkTo(methodOn(ProductController.class).createProduct(null, null)).withRel("createProduct"),
                        linkTo(methodOn(ProductController.class).updateProduct(null, null)).withRel("updateProduct"),
                        linkTo(methodOn(ProductController.class).deleteProduct(uuid, null)).withRel("deleteProduct")
                );
    }

    @PutMapping
    public ApiResponseDto<ProductResponse> updateProduct(@RequestBody ProductUpdateDto dto, @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        Product updatedProduct = productService.update(dto);
        ProductResponse response = ProductResponse.from(updatedProduct);
        String message = String.format(messageSource.getMessage("product.update.message", null, locale), updatedProduct.getName());
        log.info(response.toString());
        return ApiResponseUtils.ok(response, message);
    }

    @PostMapping
    public ApiResponseDto<ProductResponse> createProduct(@RequestBody ProductCreateDto dto, Locale locale) {
        Product createdProduct = productService.create(dto);
        ProductResponse response = ProductResponse.from(createdProduct);
        String message = String.format(messageSource.getMessage("product.create.message", null, locale), createdProduct.getName());
        log.info(response.toString());
        return ApiResponseUtils.ok(response, message);
    }

    @DeleteMapping("/{uuid}")
    public ApiResponseDto<Void> deleteProduct(@PathVariable String uuid, Locale locale) {
        productService.delete(uuid);
        String message = String.format(messageSource.getMessage("product.delete.message", null, locale), uuid);
        log.info("delete " + uuid);
        return ApiResponseUtils.ok(message);
    }
}

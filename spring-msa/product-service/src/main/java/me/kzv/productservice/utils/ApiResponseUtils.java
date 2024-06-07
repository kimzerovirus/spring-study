package me.kzv.productservice.utils;

import me.kzv.productservice.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;

public class ApiResponseUtils {
    public static ApiResponseDto ok(String message) {
        return ok(null, message);
    }

    public static <T> ApiResponseDto<T> ok(T data) {
        return ok(data, "OK");
    }

    public static <T> ApiResponseDto ok(T data, String message){
        return ApiResponseDto.ok(data, message);
    }

    public static <T> ApiResponseDto of(T data, String message, HttpStatus httpStatus) {
        return ApiResponseDto.of(data, message, httpStatus);
    }
}

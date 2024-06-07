package me.kzv.productservice.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;

public class ApiResponseDto<T> extends RepresentationModel<ApiResponseDto<T>> {
    private T data;
    private String message;
    private HttpStatus httpStatus;

    public ApiResponseDto() {
        super();
    }

    public ApiResponseDto(T data, String message, HttpStatus httpStatus) {
        this.data = data;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public static <T> ApiResponseDto ok(T data, String message){
        return new ApiResponseDto(data, message, HttpStatus.OK);
    }

    public static <T> ApiResponseDto of(T data, String message, HttpStatus httpStatus){
        return new ApiResponseDto(data, message, httpStatus);
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
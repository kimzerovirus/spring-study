package me.kzv.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApiResponseDto<T> extends RepresentationModel<ApiResponseDto<T>> {
    private Object data;
    private String message;
    private HttpStatus httpStatus;

    public static <T> ApiResponseDto<T> ok(T data, String message){
        return new ApiResponseDto<>(data, message, HttpStatus.OK);
    }

    public static <T> ApiResponseDto<T> of(T data, String message, HttpStatus httpStatus){
        return new ApiResponseDto<>(data, message, httpStatus);
    }

}
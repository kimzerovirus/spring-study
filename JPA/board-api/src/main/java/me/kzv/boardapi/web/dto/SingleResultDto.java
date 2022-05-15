package me.kzv.boardapi.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResultDto<T> extends CommonResultDto {
    private T data;
}

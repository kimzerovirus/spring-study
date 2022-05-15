package me.kzv.boardapi.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResultDto<T> extends CommonResultDto {
    private List<T> list;
}
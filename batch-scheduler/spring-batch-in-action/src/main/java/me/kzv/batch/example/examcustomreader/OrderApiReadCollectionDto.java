package me.kzv.batch.example.examcustomreader;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderApiReadCollectionDto {
    private String code;
    private String message;
    private List<OrderApiReadDto> data;
}

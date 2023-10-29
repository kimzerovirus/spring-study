package me.kzv.batch.example.examcustomreader;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderApiReadDto {
    private String tradeNo;
    private Long amount;

    @Builder
    public OrderApiReadDto(String tradeNo, Long amount) {
        this.tradeNo = tradeNo;
        this.amount = amount;
    }
}

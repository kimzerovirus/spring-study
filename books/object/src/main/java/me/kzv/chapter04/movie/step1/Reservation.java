package me.kzv.chapter04.movie.step1;

import lombok.*;
import me.kzv.chapter04.money.Money;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Customer customer;
    private Screening screening;
    private Money fee;
    private int AudienceCount;
}

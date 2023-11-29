package me.kzv.reactspring.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;
}

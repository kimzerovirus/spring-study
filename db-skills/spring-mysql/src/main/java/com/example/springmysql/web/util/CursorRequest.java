package com.example.springmysql.web.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CursorRequest {
    public static final Long NONE_KEY = -1L;

    private Long key;
    private int size;

    public Boolean hasKey() {
        return key != null && !key.equals(NONE_KEY);
    }

    public CursorRequest next(Long key) {
        return new CursorRequest(key, size);
    }
}

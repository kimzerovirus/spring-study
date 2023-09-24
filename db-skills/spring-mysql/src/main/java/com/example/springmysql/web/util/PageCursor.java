package com.example.springmysql.web.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PageCursor<T> {
    private CursorRequest nextCursorRequest;
    private List<T> body;
}

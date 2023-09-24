package com.example.springmysql.web.util;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

public class PageHelper {
    public static String orderBy(Sort sort) {
        if (sort.isEmpty()) {
            return "id DESC";
        }
        
        List<Sort.Order> orders = sort.toList();
        var orderBys = orders
                .stream()
                .map(order -> order.getProperty() + " " + order.getDirection())
                .collect(Collectors.toList());

        return String.join(", ", orderBys);
    }
}
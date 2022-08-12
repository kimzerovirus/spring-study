package me.kzv.shopapi.repository.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.kzv.shopapi.domain.Address;
import me.kzv.shopapi.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;


@Data
@EqualsAndHashCode(of = "orderId") // <- groupingBy 사용시에 묶을 기준점이 된다.
public class OrderQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemQueryDto> orderItems;

    public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }

    public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address, List<OrderItemQueryDto> orderItems) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.orderItems = orderItems;
    }
}

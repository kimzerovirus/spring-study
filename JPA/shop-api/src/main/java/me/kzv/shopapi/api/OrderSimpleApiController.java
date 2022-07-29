package me.kzv.shopapi.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.kzv.shopapi.repository.OrderSimpleQueryDto;
import me.kzv.shopapi.domain.Address;
import me.kzv.shopapi.domain.Order;
import me.kzv.shopapi.domain.OrderStatus;
import me.kzv.shopapi.repository.OrderRepository;
import me.kzv.shopapi.repository.OrderSearch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * XxxToOne [ManyToOne, OneToOne]
 * Order
 * Order -> Member
 * Order -> Delivery
 */

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
//        return orderRepository.findAllByString(new OrderSearch());
        // 무한 루프에 빠져버림 order -> member -> order -> member ... 따라서 양방향 연관관계에서 한쪽에는 JsonIgnore 를 걸어줘야 한다.
        // No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and no properties discovered to create BeanSerializer
        // - Jackson 라이브러리가 데이터를 변환하다가 알 수 없는 타입이라는 것으로 지연로딩으로 설정된 엔티티는 proxy(ByteBuddyInterceptor) 에서 데이터를 가져오므로 타입을 모르게 되어 에러 발생
        // 사실 엔티티를 바로 프레젠테이션 계층에 보여주므로 인한 문제이기도 하다.

        // 해결법 1
        // Hibernate5Module 모듈을 통해 해결

        // 해결법 2
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); // Lazy 강제 초기화
            order.getDelivery().getAddress(); // Lazy 강제 초기화
        }

        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        // ORDER 2개
        // 첫번째 쿼리 결과로 2개의 쿼리가 또 발생
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        // 회원 N(2), 배송 N(2)
        List<SimpleOrderDto> result = orders.stream().map(o -> new SimpleOrderDto(o)).collect(Collectors.toList());

        //==결과==//
        // N + 1 -> 주문 1 + [회원 N(2), 배송 N(2) ] 총 5번의 쿼리가 실행되게 된다.
        /*
            즉, order 조회 1번 이후
            order -> member 지연로딩 조회 N번
            order -> delivery 지연로딩 조회 N번
            이 발생하여
            최악의 경우 order 결과가 2개인 경우에 1 + 2 + 2 번의 쿼리가 실행 되게 된다.
             - 지연로딩은 영속성 컨텍스트에서 조회하므로, 이미 조회된 경우 쿼리를 생략하게 된다.
         */
        return result;

//        return orderRepository.findAllByString(new OrderSearch()).stream()
//                .map(SimpleOrderDto::new)
//                .collect(Collectors.toList());
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName(); // LAZY 초기화 - member 가져온다.
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); // LAZY 초기화 - delivery 가져온다.
        }
    }

    // TODO :: v1 v2 는 테이블 3개와 관련되어 수 많은 쿼리가 발생하는 문제점이 있다.

    /**
     * FetchJoin 으로 성능 최적화 하기
     */
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        // 필요한 데이터를 fetch join 을 이용하여 미리 데이터를 긁어 와서 LAZY 로딩의 문제점을 해결한다.
        List<Order> orders = orderRepository.findAllWithMemberDelivery();

        return orders.stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }

    /**
     * JPA 에서 바로 DTO 로 조회하기
     */

    public List<OrderSimpleQueryDto> ordersV4() {
        return orderRepository.findOrderDto();
    }

    // TODO :: v4 는 필요한 데이터만 조회하기 때문에 성능이 v3 보다 조금의 이점은 있을지 모르지만 엔티티로 조회해오는 v3 에 비해 재사용성이 떨어진다는 단점이 있고 또한 프레젠테이션 계층인 api 스펙이 그대로 repository 에 들어가게 되어 계층의 경계가 허물어진다는 문제점도 있다.

}


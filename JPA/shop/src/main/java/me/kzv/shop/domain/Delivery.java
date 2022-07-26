package me.kzv.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // ordinal 은 숫자라서 나중에 테이블 변경이 있다면 숫자가 밀려서 기존데이터와의 혼동이 올 수 있다.
    private DeliveryStatus status; // [READY, COMP]

}

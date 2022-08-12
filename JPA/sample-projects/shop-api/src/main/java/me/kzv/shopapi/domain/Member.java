package me.kzv.shopapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @JsonIgnore // api 호출 시에 이 정보는 빠지게 됨 -> 프레젠테이션 계층은 상황마다 달라지므로 dto 로 보내는게 더 좋음
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL) // order 클래스의 member 가 주인이다.
    private List<Order> orders = new ArrayList<>();
}

package me.kzv.jpabasic.persistence.team;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kzv.jpabasic.persistence.member.Member;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Team {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String teamName;

    // 실제 db에서는 fk가 되는 값을 Many쪽이 갖게 된다. 따라서 주인은 member 클래스의 team 필드이다.
    // mappedBy를 사용하여 연관관계의 주인을 설정하거나 targetEntity 를 사용해서 설정할 수도 있다.
    // mappedBy는 논리적인 매핑으로 참조하는 필드를 가르킨다.
    // 실제 물리적인 매핑은 @joinColumn 으로 지정 가능하며 one쪽에서도 many쪽에서도 가능하지만 될수 있으면, 실제 fk를 가지고 있는 ManyToOne 쪽에서 걸어준다.
    // mappedBy가 필요한 이유는 양방향 매핑에서 실제 fk가 되는 필드가 연관관계의 주인(실제 fk 필드)이 되고
    // 그 반대편 엔티티에게 그러한 정보를 알려주기 위해 설정하여 한쪽에서만 관리할 수 있도록 하는 것이다.
    @OneToMany(mappedBy = "team", targetEntity = Member.class) // targetEntity 는 제네릭이 없어도 타입 정보를 알 수 있게 해준다. <- 사실상 생략하고 제네릭 쓰자
    private List memberList = new ArrayList<>();
}

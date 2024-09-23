package me.kzv.jpabasic.persistence.strategy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract public class TablePerClass {
    // DB에게 전략을 위임하는 identity 옵션을 TABLE_PER_CLASS에서는 사용할 수 없다.
    // TABLE_PER_CLASS는 통합으로 관리하는 주체가 없기 때문에 몇번까지 ID를 사용하였는지 알 수가 없다. 각각의 테이블이 ID를 생성하는 시퀀스 등을 가지고 있으므로
    // 따라서 TABLE_PER_CLASS 에서는 GenerationType.IDENTITY 옵션을 사용할 수 없다.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String person;
}

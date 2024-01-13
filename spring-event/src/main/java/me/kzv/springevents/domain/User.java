package me.kzv.springevents.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kzv.springevents.defaultevent.CustomEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User extends AbstractAggregateRoot<User> {

    /**
     * @DomainEvents를 사용하면 Aggregate 에 작성해야 하는 코드가 많아지는데
     * AbstractAggregateRoot 를 사용하면 작성해야 하는 코드를 매우 단순화할 수 있다.
     * 다음과 같이 AbstractAggregateRoot 를 상속받고
     * registerEvent 를 통해 이벤트가 등록되도록 처리만 하면 된다.
     */

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;
    private String userId;
    private int age;
    private LocalDateTime created;

    // domain operation - AbstractAggregateRoot Template 사용
    public void addAge() {
        this.age++;
        this.registerEvent(new CustomEvent(this, "user age is " + this.age));
    }
}


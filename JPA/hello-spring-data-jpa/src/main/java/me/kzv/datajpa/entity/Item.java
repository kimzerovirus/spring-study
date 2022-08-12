package me.kzv.datajpa.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
  GeneratedValue 를 사용하면 id 가 없기 때문에 새로운 엔티티로 인식하지만 직접 id 를 넣어준다면
  JPA 의 기본 생성 전략인 merge 를 호출하기 때문에 그 값을 DB 에서 select 하여 찾는 과정이 있다.
  따라서 Persistable 어노테이션을 사용하여 merge 사용을 피하도록 설정하자
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item implements Persistable<String> {

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;

    public Item(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        // 새로운 것인지 아닌지를 판별 조건을 작성한다.
        return createdDate == null;
    }
}

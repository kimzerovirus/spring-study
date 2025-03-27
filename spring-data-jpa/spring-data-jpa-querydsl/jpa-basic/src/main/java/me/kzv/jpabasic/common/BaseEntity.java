package me.kzv.jpabasic.common;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.Where;

@Where(clause = " deleted='N'")
@MappedSuperclass
abstract public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean deleted = false;

    public void delete() {
       this.deleted = true;
    }
}

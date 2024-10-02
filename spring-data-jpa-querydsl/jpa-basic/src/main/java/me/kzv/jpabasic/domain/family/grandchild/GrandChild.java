package me.kzv.jpabasic.domain.family.grandchild;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kzv.jpabasic.common.BaseEntity;
import me.kzv.jpabasic.domain.family.child.Child;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GrandChild extends BaseEntity {
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id")
    private Child child;
}

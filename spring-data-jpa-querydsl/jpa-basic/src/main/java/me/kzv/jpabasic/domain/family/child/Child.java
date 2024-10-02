package me.kzv.jpabasic.domain.family.child;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kzv.jpabasic.common.BaseEntity;
import me.kzv.jpabasic.domain.family.grandchild.GrandChild;
import me.kzv.jpabasic.domain.family.parent.Parent;

import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Child extends BaseEntity {
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<GrandChild> grandChildren = new ArrayList<>();
}


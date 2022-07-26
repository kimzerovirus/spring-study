package me.kzv.shop.domain;

import lombok.Getter;
import lombok.Setter;
import me.kzv.shop.domain.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany // 관계형 db 는 다대다 관계가 바로 안되고 다대일 일대다로 중간 테이블을 통하여 조인 되어야 한다. 하지만 ManyToMany는 중간 테이블에 컬럼을 추가할 수 없고, 세밀하게 쿼리를 실행하기에 적합하지 않으므로 실무사용을 지양한다.  ManyToOne -> OneToMany 로 풀어내자
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}

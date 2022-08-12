package me.kzv.shopapi.domain.item;

import lombok.Getter;
import lombok.Setter;
import me.kzv.shopapi.domain.Category;
import me.kzv.shopapi.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 하나의 테이블로 생성해주는 전략
@DiscriminatorColumn(name = "dtype") //부모 클래스에 선언한며 하위 클래스를 구분하는 용도의 컬럼 [default = DTYPE]
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items") // 실무에서 쓰면 복잡해서 문제를 야기할 수 있음, 따라서 안씀
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    /**
     * 재고 수량 증가
     */
    public void addStock(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }

    /**
     * 재고 수량 감소
     */
    public void removeStock(int stockQuantity) {
        int restStock = this.stockQuantity - stockQuantity;
        if(restStock < 0 ){
            throw new NotEnoughStockException("need more stock");
        }

        this.stockQuantity = restStock;
    }
}

/*
    SINGLE_TABLE : 하나의 테이블로 생성해주는 전략
    TABLE_PER_CLASS : 상속받은 클래스 하나하나를 다른 테이블로 생성하는 전략
 */

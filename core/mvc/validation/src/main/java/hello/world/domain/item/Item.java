package hello.world.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.ScriptAssert;

@Data
@ScriptAssert(lang="javascript", script = "_this.price * _this.quantity >= 10000", message = "총 합은 10,000 원을 넘겨야 함")
public class Item {

//    @NotNull(groups = UpdateCheck.class)
    private Long id;
//    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Range(min = 1000, max=1000000)
    private Integer price;

//    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//    @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

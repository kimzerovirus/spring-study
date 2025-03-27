package me.kzv.jpabasic.domain.store;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kzv.jpabasic.common.BaseEntity;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Entity
public class StoreHistory extends BaseEntity {

    private String storeName;
    private String productNames;
    private String employeeNames;

    public StoreHistory(Store store, List<Product> products, List<Employee> employees) {
        this.storeName = store.getName();
        this.productNames = products.stream()
                .map(Product::getName)
                .collect(Collectors.joining( "," ));

        this.employeeNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining( "," ));
    }
}

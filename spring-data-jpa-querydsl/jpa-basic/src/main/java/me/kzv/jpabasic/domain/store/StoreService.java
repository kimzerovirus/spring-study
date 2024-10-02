package me.kzv.jpabasic.domain.store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @SuppressWarnings("DuplicatedCode")
    public long find() {
        List<Store> stores = storeRepository.findAll();
        long productSum = stores.stream()
                .map(Store::getProducts)
                .flatMap(Collection::stream)
                .mapToLong(Product::getPrice)
                .sum();

        List<String> employeeNames = stores.stream()
                .map(Store::getEmployees)
                .flatMap(Collection::stream)
                .map(Employee::getName)
                .toList();

        log.info(employeeNames.toString());

        return productSum;
    }

    @SuppressWarnings("DuplicatedCode")
    public long findByFetchJoin() {
        List<Store> stores = storeRepository.findAllByFetchJoin();
        long productSum = stores.stream()
                .map(Store::getProducts)
                .flatMap(Collection::stream)
                .mapToLong(Product::getPrice)
                .sum();

        List<String> employeeNames = stores.stream()
                .map(Store::getEmployees)
                .flatMap(Collection::stream)
                .map(Employee::getName)
                .toList();

        log.info(employeeNames.toString());

        return productSum;
    }

    @SuppressWarnings("DuplicatedCode")
    public long findByQuerydsl() {
        List<Store> stores = storeRepository.findAllByQuerydsl();
        long productSum = stores.stream()
                .map(Store::getProducts)
                .flatMap(Collection::stream)
                .mapToLong(Product::getPrice)
                .sum();

        List<String> employeeNames = stores.stream()
                .map(Store::getEmployees)
                .flatMap(Collection::stream)
                .map(Employee::getName)
                .toList();

        log.info(employeeNames.toString());

        return productSum;
    }
}

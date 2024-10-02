package me.kzv.jpabasic.domain.store;

import java.util.List;

public interface StoreQuerydslRepositoryCustom {
    List<Store> findAllByQuerydsl();
}

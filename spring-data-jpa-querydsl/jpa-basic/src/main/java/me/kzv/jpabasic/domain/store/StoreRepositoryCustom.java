package me.kzv.jpabasic.domain.store;

import java.util.List;

/**
 * StoreQueryDslRepository 는 인식을 못함..
 * entity명 + repository 이런식으로 클래스명을 정해줘야하는 듯
 * No property found for type
 */
public interface StoreRepositoryCustom {
    List<Store> findAllByQuerydsl();
}

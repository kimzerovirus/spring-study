package me.kzv.jpabasic.domain.store;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static me.kzv.jpabasic.domain.store.QStore.store;

@RequiredArgsConstructor
public class StoreQueryDslRepositoryImpl implements StoreQuerydslRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Store> findAllByQuerydsl() {
        return queryFactory.selectFrom(store)
                .join(store.employees).fetchJoin()
                .join(store.products).fetchJoin()
                .fetch();
    }
}
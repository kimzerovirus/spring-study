package me.kzv.helloquerydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.kzv.helloquerydsl.entity.TestEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static me.kzv.helloquerydsl.entity.QTestEntity.testEntity;

@Repository
public class TestCustomRepository {
    private JPAQueryFactory queryFactory;

    public TestCustomRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<TestEntity> getByDate(){
        LocalDate now = LocalDate.now();
        int month = now.getMonth().getValue();

        System.out.println(month);
        return queryFactory.selectFrom(testEntity)
                .where(testEntity.date.month().in(List.of(month, month - 1, month - 2))).fetch();
    }
}

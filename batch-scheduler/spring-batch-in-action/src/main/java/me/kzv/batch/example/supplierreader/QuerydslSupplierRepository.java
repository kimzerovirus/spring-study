package me.kzv.batch.example.supplierreader;

import me.kzv.batch.entity.student.QTeacher;
import me.kzv.batch.entity.student.Teacher;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class QuerydslSupplierRepository {
    private final JPAQueryFactory queryFactory;

    public List<Teacher> findAllByPaging(String name, int offset, int limit) {
        QTeacher teacher = QTeacher.teacher;
        return queryFactory
                .selectFrom(teacher)
                .where(teacher.name.eq(name))
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<Teacher> findAllByNewTxPaging(String name, int offset, int limit) {
        QTeacher teacher = QTeacher.teacher;
        return queryFactory
                .selectFrom(teacher)
                .where(teacher.name.eq(name))
                .offset(offset)
                .limit(limit)
                .fetch();
    }
}


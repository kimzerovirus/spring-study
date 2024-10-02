package me.kzv.jpabasic.domain.strategy;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTablePerClass is a Querydsl query type for TablePerClass
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTablePerClass extends EntityPathBase<TablePerClass> {

    private static final long serialVersionUID = -1537467686L;

    public static final QTablePerClass tablePerClass = new QTablePerClass("tablePerClass");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath person = createString("person");

    public QTablePerClass(String variable) {
        super(TablePerClass.class, forVariable(variable));
    }

    public QTablePerClass(Path<? extends TablePerClass> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTablePerClass(PathMetadata metadata) {
        super(TablePerClass.class, metadata);
    }

}


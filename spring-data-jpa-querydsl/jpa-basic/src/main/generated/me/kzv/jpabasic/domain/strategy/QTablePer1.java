package me.kzv.jpabasic.domain.strategy;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTablePer1 is a Querydsl query type for TablePer1
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTablePer1 extends EntityPathBase<TablePer1> {

    private static final long serialVersionUID = -807767373L;

    public static final QTablePer1 tablePer1 = new QTablePer1("tablePer1");

    public final QTablePerClass _super = new QTablePerClass(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath person = _super.person;

    public final StringPath title = createString("title");

    public QTablePer1(String variable) {
        super(TablePer1.class, forVariable(variable));
    }

    public QTablePer1(Path<? extends TablePer1> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTablePer1(PathMetadata metadata) {
        super(TablePer1.class, metadata);
    }

}


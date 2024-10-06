package me.kzv.jpabasic.domain.strategy.taberperclass;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTablePer2 is a Querydsl query type for TablePer2
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTablePer2 extends EntityPathBase<TablePer2> {

    private static final long serialVersionUID = -59334717L;

    public static final QTablePer2 tablePer2 = new QTablePer2("tablePer2");

    public final QTablePerClass _super = new QTablePerClass(this);

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath person = _super.person;

    public QTablePer2(String variable) {
        super(TablePer2.class, forVariable(variable));
    }

    public QTablePer2(Path<? extends TablePer2> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTablePer2(PathMetadata metadata) {
        super(TablePer2.class, metadata);
    }

}


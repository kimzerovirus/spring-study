package me.kzv.jpabasic.domain.strategy.singletable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGrape is a Querydsl query type for Grape
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGrape extends EntityPathBase<Grape> {

    private static final long serialVersionUID = 644803572L;

    public static final QGrape grape = new QGrape("grape");

    public final QFruit _super = new QFruit(this);

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QGrape(String variable) {
        super(Grape.class, forVariable(variable));
    }

    public QGrape(Path<? extends Grape> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGrape(PathMetadata metadata) {
        super(Grape.class, metadata);
    }

}


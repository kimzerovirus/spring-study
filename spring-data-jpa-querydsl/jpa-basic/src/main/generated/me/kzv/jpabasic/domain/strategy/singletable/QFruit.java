package me.kzv.jpabasic.domain.strategy.singletable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFruit is a Querydsl query type for Fruit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFruit extends EntityPathBase<Fruit> {

    private static final long serialVersionUID = 643899069L;

    public static final QFruit fruit = new QFruit("fruit");

    public final me.kzv.jpabasic.common.QBaseEntity _super = new me.kzv.jpabasic.common.QBaseEntity(this);

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QFruit(String variable) {
        super(Fruit.class, forVariable(variable));
    }

    public QFruit(Path<? extends Fruit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFruit(PathMetadata metadata) {
        super(Fruit.class, metadata);
    }

}


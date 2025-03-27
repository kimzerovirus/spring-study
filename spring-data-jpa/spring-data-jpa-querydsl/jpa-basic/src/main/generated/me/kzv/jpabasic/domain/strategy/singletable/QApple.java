package me.kzv.jpabasic.domain.strategy.singletable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QApple is a Querydsl query type for Apple
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApple extends EntityPathBase<Apple> {

    private static final long serialVersionUID = 639217155L;

    public static final QApple apple = new QApple("apple");

    public final QFruit _super = new QFruit(this);

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QApple(String variable) {
        super(Apple.class, forVariable(variable));
    }

    public QApple(Path<? extends Apple> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApple(PathMetadata metadata) {
        super(Apple.class, metadata);
    }

}


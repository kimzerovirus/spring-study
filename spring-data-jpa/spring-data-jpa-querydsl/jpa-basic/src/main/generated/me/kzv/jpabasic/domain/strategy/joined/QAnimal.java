package me.kzv.jpabasic.domain.strategy.joined;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAnimal is a Querydsl query type for Animal
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnimal extends EntityPathBase<Animal> {

    private static final long serialVersionUID = -116815086L;

    public static final QAnimal animal = new QAnimal("animal");

    public final me.kzv.jpabasic.common.QBaseEntity _super = new me.kzv.jpabasic.common.QBaseEntity(this);

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public QAnimal(String variable) {
        super(Animal.class, forVariable(variable));
    }

    public QAnimal(Path<? extends Animal> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnimal(PathMetadata metadata) {
        super(Animal.class, metadata);
    }

}


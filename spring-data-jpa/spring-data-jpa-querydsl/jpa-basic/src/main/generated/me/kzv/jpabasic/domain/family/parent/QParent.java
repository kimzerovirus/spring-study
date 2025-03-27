package me.kzv.jpabasic.domain.family.parent;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParent is a Querydsl query type for Parent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParent extends EntityPathBase<Parent> {

    private static final long serialVersionUID = -685756816L;

    public static final QParent parent = new QParent("parent");

    public final me.kzv.jpabasic.common.QBaseEntity _super = new me.kzv.jpabasic.common.QBaseEntity(this);

    public final ListPath<me.kzv.jpabasic.domain.family.child.Child, me.kzv.jpabasic.domain.family.child.QChild> children = this.<me.kzv.jpabasic.domain.family.child.Child, me.kzv.jpabasic.domain.family.child.QChild>createList("children", me.kzv.jpabasic.domain.family.child.Child.class, me.kzv.jpabasic.domain.family.child.QChild.class, PathInits.DIRECT2);

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public QParent(String variable) {
        super(Parent.class, forVariable(variable));
    }

    public QParent(Path<? extends Parent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParent(PathMetadata metadata) {
        super(Parent.class, metadata);
    }

}


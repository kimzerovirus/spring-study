package me.kzv.jpabasic.domain.family.child;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChild is a Querydsl query type for Child
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChild extends EntityPathBase<Child> {

    private static final long serialVersionUID = 1191903756L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChild child = new QChild("child");

    public final me.kzv.jpabasic.common.QBaseEntity _super = new me.kzv.jpabasic.common.QBaseEntity(this);

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final ListPath<me.kzv.jpabasic.domain.family.grandchild.GrandChild, me.kzv.jpabasic.domain.family.grandchild.QGrandChild> grandChildren = this.<me.kzv.jpabasic.domain.family.grandchild.GrandChild, me.kzv.jpabasic.domain.family.grandchild.QGrandChild>createList("grandChildren", me.kzv.jpabasic.domain.family.grandchild.GrandChild.class, me.kzv.jpabasic.domain.family.grandchild.QGrandChild.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final me.kzv.jpabasic.domain.family.parent.QParent parent;

    public QChild(String variable) {
        this(Child.class, forVariable(variable), INITS);
    }

    public QChild(Path<? extends Child> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChild(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChild(PathMetadata metadata, PathInits inits) {
        this(Child.class, metadata, inits);
    }

    public QChild(Class<? extends Child> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new me.kzv.jpabasic.domain.family.parent.QParent(forProperty("parent")) : null;
    }

}


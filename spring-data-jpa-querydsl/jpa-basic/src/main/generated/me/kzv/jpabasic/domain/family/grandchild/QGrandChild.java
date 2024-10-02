package me.kzv.jpabasic.domain.family.grandchild;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGrandChild is a Querydsl query type for GrandChild
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGrandChild extends EntityPathBase<GrandChild> {

    private static final long serialVersionUID = -1743651184L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGrandChild grandChild = new QGrandChild("grandChild");

    public final me.kzv.jpabasic.common.QBaseEntity _super = new me.kzv.jpabasic.common.QBaseEntity(this);

    public final me.kzv.jpabasic.domain.family.child.QChild child;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public QGrandChild(String variable) {
        this(GrandChild.class, forVariable(variable), INITS);
    }

    public QGrandChild(Path<? extends GrandChild> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGrandChild(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGrandChild(PathMetadata metadata, PathInits inits) {
        this(GrandChild.class, metadata, inits);
    }

    public QGrandChild(Class<? extends GrandChild> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new me.kzv.jpabasic.domain.family.child.QChild(forProperty("child"), inits.get("child")) : null;
    }

}


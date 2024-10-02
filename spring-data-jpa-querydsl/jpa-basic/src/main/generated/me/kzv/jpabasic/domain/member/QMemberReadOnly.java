package me.kzv.jpabasic.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberReadOnly is a Querydsl query type for MemberReadOnly
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberReadOnly extends EntityPathBase<MemberReadOnly> {

    private static final long serialVersionUID = 1781505444L;

    public static final QMemberReadOnly memberReadOnly = new QMemberReadOnly("memberReadOnly");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memberName = createString("memberName");

    public final NumberPath<Long> teamId = createNumber("teamId", Long.class);

    public QMemberReadOnly(String variable) {
        super(MemberReadOnly.class, forVariable(variable));
    }

    public QMemberReadOnly(Path<? extends MemberReadOnly> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberReadOnly(PathMetadata metadata) {
        super(MemberReadOnly.class, metadata);
    }

}


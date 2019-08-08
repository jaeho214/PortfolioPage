package com.dev.portfolio.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberRole is a Querydsl query type for MemberRole
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberRole extends EntityPathBase<MemberRole> {

    private static final long serialVersionUID = 1421235402L;

    public static final QMemberRole memberRole = new QMemberRole("memberRole");

    public final StringPath roleName = createString("roleName");

    public final NumberPath<Long> roleNo = createNumber("roleNo", Long.class);

    public QMemberRole(String variable) {
        super(MemberRole.class, forVariable(variable));
    }

    public QMemberRole(Path<? extends MemberRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberRole(PathMetadata metadata) {
        super(MemberRole.class, metadata);
    }

}


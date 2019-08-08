package com.dev.portfolio.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -149972428L;

    public static final QMember member = new QMember("member1");

    public final StringPath email = createString("email");

    public final StringPath enName = createString("enName");

    public final StringPath id = createString("id");

    public final StringPath koName = createString("koName");

    public final StringPath phoneNum = createString("phoneNum");

    public final StringPath pw = createString("pw");

    public final ListPath<MemberRole, QMemberRole> roles = this.<MemberRole, QMemberRole>createList("roles", MemberRole.class, QMemberRole.class, PathInits.DIRECT2);

    public final StringPath sex = createString("sex");

    public final StringPath socialNum = createString("socialNum");

    public final StringPath uri = createString("uri");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}


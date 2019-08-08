package com.dev.portfolio.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContents is a Querydsl query type for Contents
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContents extends EntityPathBase<Contents> {

    private static final long serialVersionUID = 1987505204L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContents contents = new QContents("contents");

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    public final NumberPath<Long> contentNo = createNumber("contentNo", Long.class);

    public final QMember member;

    public QContents(String variable) {
        this(Contents.class, forVariable(variable), INITS);
    }

    public QContents(Path<? extends Contents> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContents(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContents(PathMetadata metadata, PathInits inits) {
        this(Contents.class, metadata, inits);
    }

    public QContents(Class<? extends Contents> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}


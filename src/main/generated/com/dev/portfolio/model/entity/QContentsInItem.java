package com.dev.portfolio.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContentsInItem is a Querydsl query type for ContentsInItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContentsInItem extends EntityPathBase<ContentsInItem> {

    private static final long serialVersionUID = -971193556L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContentsInItem contentsInItem = new QContentsInItem("contentsInItem");

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    public final NumberPath<Long> contentNo = createNumber("contentNo", Long.class);

    public final QItem item;

    public final QMember member;

    public QContentsInItem(String variable) {
        this(ContentsInItem.class, forVariable(variable), INITS);
    }

    public QContentsInItem(Path<? extends ContentsInItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContentsInItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContentsInItem(PathMetadata metadata, PathInits inits) {
        this(ContentsInItem.class, metadata, inits);
    }

    public QContentsInItem(Class<? extends ContentsInItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}


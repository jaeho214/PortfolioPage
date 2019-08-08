package com.dev.portfolio.repository.ItemRepositoryImpl;

import com.dev.portfolio.model.dto.ContentsInItemDto;
import com.dev.portfolio.model.dto.ItemDto;
import com.dev.portfolio.model.entity.Item;
import com.dev.portfolio.model.entity.QContentsInItem;
import com.dev.portfolio.model.entity.QItem;
import com.dev.portfolio.repository.custom.ItemRepositoryCustom;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class ItemRepositoryImpl extends QuerydslRepositorySupport implements ItemRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;
    private QItem item = QItem.item;
    private QContentsInItem contentsInItem = QContentsInItem.contentsInItem;
    //private QMemberEntity memberEntity = QMemberEntity.memberEntity;

    public ItemRepositoryImpl() { super(Item.class);}

    @Override
    public List<ItemDto> getItems(String user_id){
        JPAQuery<ItemDto> jpaQuery = new JPAQuery<>(entityManager);
        jpaQuery.select(item)
                .from(item)
                .where(item.member.id.eq(user_id));
        List<ItemDto> items = jpaQuery.fetch();
        return items;
    }

    @Override
    public List<ContentsInItemDto> getContentsInItem(String user_id, Long item_no) {
        JPAQuery<ContentsInItemDto> jpaQuery = new JPAQuery<>(entityManager);
        jpaQuery.select(contentsInItem)
                .from(item)
                .join(contentsInItem)
                .where(contentsInItem.item.ino.eq(item_no).and(item.member.id.eq(user_id)));
        List<ContentsInItemDto> contents = jpaQuery.fetch();
        return contents;
    }

    @Override
    public void deleteContentsInItem(String user_id, Long item_no, Long contents_no) {
        JPADeleteClause deleteContents = new JPADeleteClause(entityManager, item);
        deleteContents.where(contentsInItem.contentNo.eq(contents_no).and(item.member.id.eq(user_id)).and(item.ino.eq(item_no)));
    }

    @Override
    public String updateItemTitle(String user_id, Long item_no, String title) {
        JPAUpdateClause updateClause = new JPAUpdateClause(entityManager,item);
        updateClause.set(item.title,title)
                .where(item.ino.eq(item_no).and(item.member.id.eq(user_id)));
        return title;
    }

    @Override
    public ContentsInItemDto updateContentsInItem(String user_id, Long item_no, Long contents_no, ContentsInItemDto contentsInItemDto) {
        JPAUpdateClause updateClause = new JPAUpdateClause(entityManager,contentsInItem);
        updateClause.set(contentsInItem.content,contentsInItemDto.getContent())
                .where(contentsInItem.contentNo.eq(contents_no).and(contentsInItem.item.ino.eq(item_no).and(item.member.id.eq(user_id))));
        return contentsInItemDto;
    }

    @Override
    public void deleteItem(String user_id, Long item_no) {
        JPADeleteClause deleteClauseItem = new JPADeleteClause(entityManager,item);
        deleteClauseItem.where(item.ino.eq(item_no).and(item.member.id.eq(user_id)));
//        JPADeleteClause deleteClauseContents = new JPADeleteClause(entityManager,contentsInItem);
//        deleteClauseContents.where(contentsInItem.item.member.id.eq(user_id).and(contentsInItem.item.ino.eq(item_no)));
//        deleteClauseItem.where(item.ino.eq(item_no));
    }





}

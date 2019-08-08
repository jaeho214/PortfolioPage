package com.dev.portfolio.model.dto;

import com.dev.portfolio.model.entity.ContentsInItem;
import com.dev.portfolio.model.entity.Item;
import com.dev.portfolio.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContentsInItemDto {
    private Long contentNo;
    private String category;
    private String content;
    private Member member;
    private Item item;

    @Builder
    public ContentsInItemDto(String category, String content, Member member, Item item){
        this.category = category;
        this.content = content;
        this.member = member;
        this.item = item;
    }

    public ContentsInItem toEntity(){
        return ContentsInItem.builder()
                .category(category)
                .content(content)
                .member(member)
                .item(item)
                .build();
    }
}

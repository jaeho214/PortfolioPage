package com.dev.portfolio.model.dto;

import com.dev.portfolio.model.entity.ContentsEntity;
import com.dev.portfolio.model.entity.ItemEntity;
import com.dev.portfolio.model.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContentsDto {
    private Long contentNo;
    private String category;
    private String content;
    private MemberEntity member;
    private ItemEntity item;

    @Builder
    public ContentsDto(String category, String content){
        this.category = category;
        this.content = content;
    }

    public ContentsEntity toEntity(){
        return ContentsEntity.builder()
                .category(category)
                .content(content)
                .member(member)
                .item(item)
                .build();
    }
}

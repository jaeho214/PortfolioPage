package com.dev.portfolio.model.dto;

/*
    contents에는 자기소개서 항목들을 추가하고
    자기소개서를 하나 만들게 되면 contentsInItem DB에 옮겨주고
    Item DB와 join해줌
    그때 contentsInItem을 처리해주기 위한 dto
 */


import com.dev.portfolio.model.entity.ContentsInItem;
import com.dev.portfolio.model.entity.Item;
import com.dev.portfolio.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
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

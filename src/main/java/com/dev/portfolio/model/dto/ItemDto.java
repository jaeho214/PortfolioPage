package com.dev.portfolio.model.dto;

import com.dev.portfolio.model.entity.Contents;
import com.dev.portfolio.model.entity.ContentsInItem;
import com.dev.portfolio.model.entity.Item;
import com.dev.portfolio.model.entity.Member;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class ItemDto {
    private Long ino;
    private Member member;
    private String title;
    //private List<ContentsInItemDto> contentsInItemDto;

    public Item toEntity() {
        return Item.builder()
                .member(member)
                .title(title)
                .build();
    }

}

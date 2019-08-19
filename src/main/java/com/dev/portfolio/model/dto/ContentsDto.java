package com.dev.portfolio.model.dto;

import com.dev.portfolio.model.entity.Contents;
import com.dev.portfolio.model.entity.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentsDto {
    private Long contentNo;
    private String category;
    private String content;
    private Member member;


    @Builder
    public ContentsDto(String category, String content,Member member){
        this.category = category;
        this.content = content;
        this.member = member;
    }

    public Contents toEntity(){
        return Contents.builder()
                .category(category)
                .content(content)
                .member(member)
                .build();
    }
}

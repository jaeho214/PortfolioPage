package com.dev.portfolio.model.entity;

/*
항목별로 자기소개서 내용 작성 후 ContentsInItem에 붙여넣기
 */

import com.dev.portfolio.model.dto.ContentsDto;
import com.dev.portfolio.model.dto.ContentsInItemDto;
import lombok.*;

import javax.persistence.*;

@Entity @Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "tbl_contents")
public class Contents {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long contentNo;

    private String category;
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Member member;

    @Builder
    public Contents(String category, String content, Member member){
        this.category = category;
        this.content = content;
        this.member = member;

    }

    public void updateContents(ContentsDto contentsDto){
        this.category = contentsDto.getCategory();
        this.content = contentsDto.getContent();
    }
}

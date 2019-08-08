package com.dev.portfolio.model.entity;

/*
항목별로 자기소개서 내용 작성
 */

import com.dev.portfolio.model.dto.ContentsInItemDto;
import lombok.*;

import javax.persistence.*;

@Entity @Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "tbl_contentsInItem")
public class ContentsInItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long contentNo;

    private String category;
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "itemCount")
    private Item item; //여러 자소서 항목들을 합칠때 +1을 해줘서 Item 테이블의 seq와 맞추어주며 구분할 수 있게끔

    @Builder
    public ContentsInItem(String category, String content, Member member, Item item){
        this.category = category;
        this.content = content;
        this.member = member;
        this.item = item;
    }

    public void updateContents(ContentsInItemDto contentsInItemDto){
        this.category = contentsInItemDto.getCategory();
        this.content = contentsInItemDto.getContent();
    }
}

package com.dev.portfolio.model.entity;

/*
항목별로 자기소개서 내용 작성
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class ContentsEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long contentNo;

    private String category;
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "index")
    private ItemEntity item; //여러 자소서 항목들을 합칠때 +!을 해줘서 Item 테이블의 seq와 맞추어주며 구분할 수 있게끔

}

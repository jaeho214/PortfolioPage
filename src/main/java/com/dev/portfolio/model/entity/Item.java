package com.dev.portfolio.model.entity;
/*
작성된 항목들을 선택하여 자기소개서 생성(수정가능) 작성된 항목들 모아서 하나의 자소서로 만들기
 */

import lombok.*;

import javax.persistence.*;

@Entity @Setter @Getter
@NoArgsConstructor
@Table(name = "tbl_item")
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long ino;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Member member;

    private String title;

    @Builder
    public Item(Long ino, Member member, String title){
        this.ino = ino;
        this.member = member;
        this.title = title;
    }


}

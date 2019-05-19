package com.dev.portfolio.model.entity;
/*
작성된 항목들을 선택하여 자기소개서 생성(수정가능) 작성된 항목들 모아서 하나의 자소서로 만들기
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long ino;

    @ManyToOne
    @JoinColumn(name = "userId")
    private MemberEntity member;

    private String title;

}

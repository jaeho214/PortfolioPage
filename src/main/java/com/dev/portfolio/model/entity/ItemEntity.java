package com.dev.portfolio.model.entity;
/*
작성된 항목들을 선택하여 자기소개서 생성(수정가능)
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
    private String userId;

    private String title;

}

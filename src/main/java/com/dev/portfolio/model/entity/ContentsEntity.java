package com.dev.portfolio.model.entity;

/*
항목별로 자기소개서 내용 작성
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class ContentsEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long contentNo;

    private String category;
    private String content;
    private String userId;

    private int index;

}

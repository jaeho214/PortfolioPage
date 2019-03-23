package com.dev.portfolio.model.entity;
/*
학력사항 정보 기입
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
public class EducationEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long eno; // PK

    private String userId; //사용자 구분을 위한 사용자 id
    private String term; // 기간
    private String organ; // 학교명
    private String major; //전공명
    private String grade; //학점
    private String note; // 비고


}

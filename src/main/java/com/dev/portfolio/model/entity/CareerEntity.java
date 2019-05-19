package com.dev.portfolio.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
경력사항 기입
 */
@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class CareerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long careerNo; // PK

    @ManyToOne
    @JoinColumn(name = "userId")
    private MemberEntity member; //사용자 구분을 위한 사용자 id

    private String company; //회사명
    private String term; //기관
    private String department; //담당부서
    private String details; //업무 내용
    private String reason; // 퇴사사유
}

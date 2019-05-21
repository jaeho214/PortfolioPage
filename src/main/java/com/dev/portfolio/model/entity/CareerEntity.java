package com.dev.portfolio.model.entity;

import lombok.*;

import javax.persistence.*;

/*
경력사항 기입
 */
@Entity @Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "tbl_career")
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

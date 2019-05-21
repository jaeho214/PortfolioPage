package com.dev.portfolio.model.entity;
/*
학력사항 정보 기입
 */

import lombok.*;

import javax.persistence.*;

@Entity @Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "tbl_education")
public class EducationEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long eno; // PK

    @ManyToOne
    @JoinColumn(name = "userId")
    private MemberEntity member; //사용자 구분을 위한 사용자 id

    private String term; // 기간
    private String organ; // 학교명
    private String major; //전공명
    private String grade; //학점
    private String note; // 비고


}

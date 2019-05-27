package com.dev.portfolio.model.entity;
/*
학력사항 정보 기입
 */

import com.dev.portfolio.model.dto.EducationDto;
import lombok.*;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public EducationEntity(MemberEntity memberEntity, String term, String organ, String major, String grade, String note){
        this.member = memberEntity;
        this.term = term;
        this.organ = organ;
        this.major = major;
        this.grade = grade;
        this.note = note;
    }


    public void updateEducation(EducationDto educationDto){
        this.term = educationDto.getTerm();
        this.organ = educationDto.getOrgan();
        this.major = educationDto.getMajor();
        this.grade = educationDto.getGrade();
        this.note = educationDto.getNote();
    }


}

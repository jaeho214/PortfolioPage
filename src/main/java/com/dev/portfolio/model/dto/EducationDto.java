package com.dev.portfolio.model.dto;

import com.dev.portfolio.model.entity.EducationEntity;
import com.dev.portfolio.model.entity.MemberEntity;
import lombok.*;

@Getter
@AllArgsConstructor
public class EducationDto {
    private Long eno; // PK
    private MemberEntity member; //사용자 구분을 위한 사용자 id

    private String term; // 기간
    private String organ; // 학교명
    private String major; //전공명
    private String grade; //학점
    private String note; // 비고

    @Builder
    public EducationDto(String term, String organ, String major, String grade, String note){
        this.term = term;
        this.organ = organ;
        this.major = major;
        this.grade = grade;
        this.note = note;
    }

    public EducationEntity toEntity(){
        return EducationEntity.builder()
                .memberEntity(member)
                .term(term)
                .organ(organ)
                .major(major)
                .grade(grade)
                .note(note)
                .build();
    }

}

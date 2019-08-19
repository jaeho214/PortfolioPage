package com.dev.portfolio.model.dto;

import com.dev.portfolio.model.entity.Career;
import com.dev.portfolio.model.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CareerDto {
    private Long careerNo;
    private Member member; //사용자 구분을 위한 사용자 id

    private String company; //회사명
    private String term; //기관
    private String department; //담당부서
    private String details; //업무 내용
    private String reason; // 퇴사사유

    @Builder
    public CareerDto(String company, String term, String details, String department, String reason){
        this.company = company;
        this.term = term;
        this.department = department;
        this.details = details;
        this.reason = reason;
    }

    public Career toEntity(){
        return Career.builder()
                .member(member)
                .company(company)
                .term(term)
                .department(department)
                .details(details)
                .reason(reason)
                .build();
    }
}

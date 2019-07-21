package com.dev.portfolio.model.entity;

import com.dev.portfolio.model.dto.CareerDto;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Member;

/*
경력사항 기입
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

            @Builder
            public CareerEntity(MemberEntity memberEntity, String company, String term, String department, String details, String reason){
                this.member = memberEntity;
                this.company = company;
                this.term = term;
                this.department = department;
                this.details = details;
                this.reason = reason;
            }

            public void updateCareer(CareerDto careerDto){
                this.company = careerDto.getCompany();
                this.department = careerDto.getDepartment();
                this.term = careerDto.getTerm();
                this.details = careerDto.getDetails();
                this.reason = careerDto.getReason();
    }



}

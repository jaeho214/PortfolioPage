package com.dev.portfolio.model.entity;

import lombok.*;

import javax.persistence.*;

/*
자격증 및 특기사항 기입
 */

@Entity @Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tbl_certificate")
public class CertificateEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long CertiNo; // PK

    @ManyToOne
    @JoinColumn(name = "userId")
    private MemberEntity member; //사용자 구분을 위한 사용자 id

    private String Certification; // 자격사항
    private String getDate; //취득일자
    private String organization; // 발급기관
    private String uri;// 사본 이미지 경로
}

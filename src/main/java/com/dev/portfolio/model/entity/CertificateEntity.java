package com.dev.portfolio.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
자격증 및 특기사항 기입
 */

@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class CertificateEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long CertiNo; // PK

    private String userId; // 사용자 구분을 위한 사용자 id
    private String Certification; // 자격사항
    private String getDate; //취득일자
    private String organization; // 발급기관
    private String uri;// 사본 이미지 경로

}

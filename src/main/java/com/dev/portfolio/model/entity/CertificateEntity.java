package com.dev.portfolio.model.entity;

import com.dev.portfolio.model.dto.CertificateDto;
import lombok.*;

import javax.persistence.*;

/*
자격증 및 특기사항 기입
 */

@Entity @Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_certificate")
public class CertificateEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long certiNo; // PK

    @ManyToOne
    @JoinColumn(name = "userId")
    private MemberEntity member; //사용자 구분을 위한 사용자 id

    private String certification; // 자격사항
    private String getDate; //취득일자
    private String organization; // 발급기관
    private String uri;// 사본 이미지 경로

    @Builder
    public CertificateEntity(MemberEntity memberEntity, String certification, String getDate, String organization, String uri){
        this.member = memberEntity;
        this.certification = certification;
        this.getDate = getDate;
        this.organization = organization;
        this.uri = uri;
    }

    public void updateCertificate(CertificateDto certificateDto){
        this.certification = certificateDto.getCertification();
        this.getDate = certificateDto.getGetDate();
        this.organization = certificateDto.getOrganization();
        this.uri = certificateDto.getUri();
    }
}

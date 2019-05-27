package com.dev.portfolio.model.dto;

import com.dev.portfolio.model.entity.CertificateEntity;
import com.dev.portfolio.model.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CertificateDto {
    private Long certiNo; // PK
    private MemberEntity member; //사용자 구분을 위한 사용자 id
    private String certification; // 자격사항
    private String date; //취득일자
    private String organization; // 발급기관
    private String uri;// 사본 이미지 경로

    @Builder
    public CertificateDto(String certification, String date, String organization, String uri){
        this.certification = certification;
        this.date = date;
        this.organization = organization;
        this.uri = uri;
    }

    public CertificateEntity toEntity(){
        return CertificateEntity.builder()
                .memberEntity(member)
                .certification(certification)
                .date(date)
                .organization(organization)
                .uri(uri)
                .build();
    }
}

package com.dev.portfolio.service;
/*
자격증을 처리하는 서비스
자격증 삽입/ 자격증 수정/ 자격증 삭제/ 자격증 불러와서 출력
 */

import com.dev.portfolio.model.dto.CertificateDto;
import com.dev.portfolio.model.entity.Certificate;
import com.dev.portfolio.repository.CertificateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CertificateService {

    private CertificateRepository certificateRepository;


    //모든 자격사항의 모든 내용을 가져오는 메소드
    public List<CertificateDto> getCertificates(){
        List<CertificateDto> certificateDtoList = new ArrayList<>();
        List<Certificate> certificates = certificateRepository.findAll();
        certificates.forEach((certificate) -> {
            certificateDtoList.add(
                    CertificateDto.builder()
                            .certification(certificate.getCertification())
                            .date(certificate.getDate())
                            .organization(certificate.getOrganization())
                            .uri(certificate.getUri())
                            .build()
            );
        });

        return certificateDtoList;
    }

    //하나의 자격사항을 추가하는 메소드
    public void saveCertificate(CertificateDto certificateDto){
        certificateRepository.save(certificateDto.toEntity());
    }

    //자격사항을 수정하는 메소드
    public void updateCertificate(CertificateDto certificateDto){
        Certificate certificate = certificateRepository.findCertificateEntityByCertiNo(certificateDto.getCertiNo());
        certificate.updateCertificate(certificateDto);
        certificateRepository.save(certificate);
    }

    public void deleteCertificate(Long certiNo){
        Certificate certificate = certificateRepository.findCertificateEntityByCertiNo(certiNo);
        certificateRepository.delete(certificate);
    }
}

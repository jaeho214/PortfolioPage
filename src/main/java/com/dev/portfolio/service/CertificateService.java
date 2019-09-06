package com.dev.portfolio.service;
/*
자격증을 처리하는 서비스
자격증 삽입/ 자격증 수정/ 자격증 삭제/ 자격증 불러와서 출력
 */

import com.dev.portfolio.model.dto.CertificateDto;
import com.dev.portfolio.model.entity.Certificate;
import com.dev.portfolio.model.entity.Member;
import com.dev.portfolio.repository.CertificateRepository;
import com.dev.portfolio.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CertificateService {

    private CertificateRepository certificateRepository;
    private JwtProvider jwtProvider;

    public CertificateService(CertificateRepository certificateRepository, JwtProvider jwtProvider){
        this.certificateRepository = certificateRepository;
        this.jwtProvider = jwtProvider;
    }

    //모든 자격사항의 모든 내용을 가져오는 메소드
    public List<CertificateDto> getCertificates(String token){
        String userId = jwtProvider.getUserIdByToken(token);
        List<CertificateDto> certificateDtoList = new ArrayList<>();
        List<Certificate> certificates = certificateRepository.findAllByMember_Id(userId);
        certificates.forEach((certificate) -> certificateDtoList.add(
                CertificateDto.builder()
                        .certiNo(certificate.getCertiNo())
                        .certification(certificate.getCertification())
                        .date(certificate.getDate())
                        .organization(certificate.getOrganization())
                        .uri(certificate.getUri())
                        .build()
        ));

        return certificateDtoList;
    }

    //하나의 자격사항을 추가하는 메소드
    public void saveCertificate(String token, CertificateDto certificateDto){
        String userId = jwtProvider.getUserIdByToken(token);
        Member member = certificateDto.getMember();
        member.setId(userId);
        certificateDto.setMember(member);
        certificateRepository.save(certificateDto.toEntity());
    }

    //자격사항을 수정하는 메소드
    public void updateCertificate(String token, CertificateDto certificateDto){
        String userId = jwtProvider.getUserIdByToken(token);
        Certificate certificate = certificateRepository.findCertificateByCertiNoAndMember_Id(certificateDto.getCertiNo(), userId);
        certificate.updateCertificate(certificateDto);
        certificateRepository.save(certificate);
    }

    public void deleteCertificate(String token, Long certiNo){
        String userId = jwtProvider.getUserIdByToken(token);
        Certificate certificate = certificateRepository.findCertificateByCertiNoAndMember_Id(certiNo, userId);
        certificateRepository.delete(certificate);
    }
}

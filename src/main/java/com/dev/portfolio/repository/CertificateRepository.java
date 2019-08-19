package com.dev.portfolio.repository;

import com.dev.portfolio.model.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Certificate findCertificateByCertiNoAndMember_Id(Long certiNo, String userId);
    List<Certificate> findAllByMember_Id(String userId);
}

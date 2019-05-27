package com.dev.portfolio.repository;

import com.dev.portfolio.model.entity.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<CertificateEntity, Long> {
    CertificateEntity findCertificateEntityByCertiNo(Long certiNo);
}

package com.dev.portfolio.repository;

import com.dev.portfolio.model.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Certificate findCertificateEntityByCertiNo(Long certiNo);
}

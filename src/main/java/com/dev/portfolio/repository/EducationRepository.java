package com.dev.portfolio.repository;

import com.dev.portfolio.model.entity.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<EducationEntity, Long> {
    EducationEntity findEducationEntityByEno(Long eno);
}

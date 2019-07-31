package com.dev.portfolio.repository;

import com.dev.portfolio.model.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
    Education findEducationEntityByEno(Long eno);
}

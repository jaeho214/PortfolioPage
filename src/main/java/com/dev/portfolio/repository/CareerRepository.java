package com.dev.portfolio.repository;

import com.dev.portfolio.model.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career, Long> {
    Career findCareerEntityByCareerNo(Long careerNo);
}

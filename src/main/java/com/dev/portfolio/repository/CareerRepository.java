package com.dev.portfolio.repository;

import com.dev.portfolio.model.entity.CareerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<CareerEntity, Long> {
    CareerEntity findCareerEntityByCareerNo(Long careerNo);
}

package com.dev.portfolio.repository;

import com.dev.portfolio.model.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareerRepository extends JpaRepository<Career, Long> {
    Career findCareerByCareerNoAndMember_Id(Long careerNo, String userId);
    List<Career> findAllByMember_Id(String userId);
}

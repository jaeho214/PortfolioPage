package com.dev.portfolio.repository;

import com.dev.portfolio.model.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    Education findEducationByEnoAndMember_Id(Long eno, String userId);
    List<Education> findAllByMember_Id(String userId);

}

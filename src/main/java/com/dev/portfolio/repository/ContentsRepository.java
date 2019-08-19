package com.dev.portfolio.repository;

import com.dev.portfolio.model.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentsRepository extends JpaRepository<Contents, Long> {
    Contents findContentsByContentNoAndMember_Id(Long contentNo, String userId);
    List<Contents> findAllByMember_Id(String userId);
}

package com.dev.portfolio.repository;

import com.dev.portfolio.model.entity.ContentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepository extends JpaRepository<ContentsEntity, Long> {
    ContentsEntity findContentsEntityByContentNo(Long contentNo);
}

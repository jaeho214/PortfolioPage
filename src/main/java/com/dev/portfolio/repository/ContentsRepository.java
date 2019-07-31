package com.dev.portfolio.repository;

import com.dev.portfolio.model.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepository extends JpaRepository<Contents, Long> {
    Contents findContentsByContentNo(Long contentNo);
}

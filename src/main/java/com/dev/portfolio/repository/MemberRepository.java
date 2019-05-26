package com.dev.portfolio.repository;


import com.dev.portfolio.model.entity.CareerEntity;
import com.dev.portfolio.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
}

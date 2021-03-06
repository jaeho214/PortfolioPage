package com.dev.portfolio.repository;



import com.dev.portfolio.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findById(String id);
    void deleteById(String id);
}

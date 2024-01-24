package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Authority;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    @Transactional
    @Modifying
    public void deleteByApprovalProductId(Integer approvalProductId);
}

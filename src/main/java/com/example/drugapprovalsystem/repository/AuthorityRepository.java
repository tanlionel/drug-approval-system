package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Authority;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    @Transactional
    @Modifying
    public void deleteByApprovalProductId(Integer approvalProductId);
    @Transactional
    @Modifying
    public void deleteByProductId(Integer productId);
    public List<Authority> findByApprovalProductId(Integer approvalProductId);
    public List<Authority> findByProductId(Integer productId);
}

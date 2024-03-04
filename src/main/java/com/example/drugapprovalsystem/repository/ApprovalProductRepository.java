package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.ApprovalProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApprovalProductRepository extends JpaRepository<ApprovalProduct, Integer> {
    public Page<ApprovalProduct> findAllByNameContaining(Pageable pageable, String name);
    public Page<ApprovalProduct> findAllByNameContainingAndProductAdministrationId(Pageable pageable,
                                                                                   String name,
                                                                                   Integer ProductAdministrationId);
}

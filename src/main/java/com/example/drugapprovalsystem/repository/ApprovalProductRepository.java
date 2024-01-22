package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.ApprovalProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalProductRepository extends JpaRepository<ApprovalProduct, Integer> {

}

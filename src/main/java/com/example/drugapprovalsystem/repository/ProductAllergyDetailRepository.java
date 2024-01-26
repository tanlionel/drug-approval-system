package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.ProductAllergyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAllergyDetailRepository extends JpaRepository<ProductAllergyDetail, Integer> {
}

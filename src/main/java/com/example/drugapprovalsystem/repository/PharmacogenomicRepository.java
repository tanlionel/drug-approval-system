package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Pharmacogenomic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacogenomicRepository extends JpaRepository<Pharmacogenomic, Integer> {
}

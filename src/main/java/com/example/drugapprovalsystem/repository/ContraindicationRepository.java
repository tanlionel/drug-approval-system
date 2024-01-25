package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Contraindication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContraindicationRepository extends JpaRepository<Contraindication, Integer> {
}

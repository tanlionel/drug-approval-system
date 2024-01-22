package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Integer> {

}

package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Manufactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufactorRepository extends JpaRepository<Manufactor, Integer> {

}

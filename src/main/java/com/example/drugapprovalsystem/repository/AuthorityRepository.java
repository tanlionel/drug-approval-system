package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}

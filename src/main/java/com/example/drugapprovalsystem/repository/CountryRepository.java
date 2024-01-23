package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Country;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    public List<Country> findByNameContainingAndIsActive(Sort sort, String name, boolean isActive);

    public List<Country> findByNameContainingAndIsActive(String name, boolean isActive);
}

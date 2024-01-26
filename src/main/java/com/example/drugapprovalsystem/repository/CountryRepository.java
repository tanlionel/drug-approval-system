package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Country;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    public List<Country> findByNameContainingAndIsActive(Sort sort, String name, boolean isActive);

    public List<Country> findByNameContainingAndIsActive(String name, boolean isActive);
}

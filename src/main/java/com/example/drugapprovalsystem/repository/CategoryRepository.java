package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public List<Category> findByTitleContainingAndIsActive(Sort sort, String title, boolean isActive);

    public List<Category> findByTitleContainingAndIsActive(String title, boolean isActive);
}

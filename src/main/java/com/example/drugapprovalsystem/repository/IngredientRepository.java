package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    @Modifying
    @Transactional
    public void deleteByApprovalProductId(@Param("approvalProductId") Integer approvalProductId);
    @Modifying
    @Transactional
    public void deleteByProductId(@Param("productId") Integer approvalProductId);
    public List<Ingredient> findByApprovalProductId(Integer approvalProductId);

    public List<Ingredient> findByProductId(Integer productId);
}

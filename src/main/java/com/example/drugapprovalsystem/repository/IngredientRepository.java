package com.example.drugapprovalsystem.repository;

import com.example.drugapprovalsystem.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}

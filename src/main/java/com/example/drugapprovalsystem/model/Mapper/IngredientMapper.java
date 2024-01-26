package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.entity.*;
import com.example.drugapprovalsystem.model.DTO.product_dto.ApprovalProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_dto.DrugIngredientsRequestDTO;
import org.springframework.security.core.parameters.P;

import java.time.Instant;
import java.util.List;

public class IngredientMapper {
    public final static Ingredient mapToIngredient(DrugIngredientsRequestDTO a, Integer productId, boolean isApprovalProduct) {
        Ingredient ingredient = new Ingredient();

        ingredient.setDrug(new Drug(a.getDrugId()));
        ingredient.setStrength(a.getStrength());
        ingredient.setStrengthNumber(a.getStrengthNumber());
        ingredient.setStrengthUnit(a.getStrengthUnit());
        ingredient.setClinicallyRelevant(a.getClinicallyRelevant());

        if (isApprovalProduct)
            ingredient.setApprovalProduct(new ApprovalProduct(productId));
        else
            ingredient.setProduct(new Product(productId));

        return ingredient;
    }
}

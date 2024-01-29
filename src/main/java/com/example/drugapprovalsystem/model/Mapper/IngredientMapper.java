package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.entity.*;
import com.example.drugapprovalsystem.model.DTO.DrugIngredientsDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.DrugIngredientsResponseDTO;

public class IngredientMapper {
    public static Ingredient mapToIngredient(DrugIngredientsDTO a, Integer productId, boolean isApprovalProduct) {
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

    public static DrugIngredientsDTO mapToDrugIngredientsDTO(Ingredient a) {
        return DrugIngredientsDTO.builder()
                .drugId((a.getDrug() == null) ? null : a.getDrug().getId())
                .strength(a.getStrength())
                .strengthNumber(a.getStrengthNumber())
                .strengthUnit(a.getStrengthUnit())
                .clinicallyRelevant(a.getClinicallyRelevant())
                .build();
    }
    public static DrugIngredientsResponseDTO mapToDrugIngredientsResponseDTO(Ingredient a) {
        return DrugIngredientsResponseDTO.builder()
                .drugId((a.getDrug() == null) ? null : a.getDrug().getId())
                .name((a.getDrug() == null) ? null : a.getDrug().getName())
                .strength(a.getStrength())
                .strengthNumber(a.getStrengthNumber())
                .strengthUnit(a.getStrengthUnit())
                .clinicallyRelevant(a.getClinicallyRelevant())
                .build();
    }
}

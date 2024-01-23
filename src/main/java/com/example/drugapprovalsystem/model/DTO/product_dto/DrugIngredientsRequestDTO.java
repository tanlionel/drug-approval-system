package com.example.drugapprovalsystem.model.DTO.product_dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DrugIngredientsRequestDTO {
    private Integer drugId;
    private String strength;
    private String strengthNumber;
    private String strengthUnit;
    private String clinicallyRelevant;
}

package com.example.drugapprovalsystem.model.DTO.product_response_dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrugIngredientsResponseDTO {
    private Integer drugId;
    private String name;
    private String strength;
    private String strengthNumber;
    private String strengthUnit;
    private String clinicallyRelevant;
}

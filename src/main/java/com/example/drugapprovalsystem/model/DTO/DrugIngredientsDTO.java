package com.example.drugapprovalsystem.model.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrugIngredientsDTO {
    private Integer drugId;
    private String strength;
    private String strengthNumber;
    private String strengthUnit;
    private String clinicallyRelevant;
}

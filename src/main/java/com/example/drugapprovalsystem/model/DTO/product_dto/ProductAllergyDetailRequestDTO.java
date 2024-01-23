package com.example.drugapprovalsystem.model.DTO.product_dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAllergyDetailRequestDTO {
    private String detail;
    private String summary;
}
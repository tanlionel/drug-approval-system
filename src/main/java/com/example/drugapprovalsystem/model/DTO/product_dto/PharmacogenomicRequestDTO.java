package com.example.drugapprovalsystem.model.DTO.product_dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PharmacogenomicRequestDTO {
    private String indication;
    private String pharmacodynamic;
    private String mechanismOfAction;
    private String asorption;
    private String toxicity;
}
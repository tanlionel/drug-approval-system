package com.example.drugapprovalsystem.model.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PharmacogenomicDTO {
    private String indication;
    private String pharmacodynamic;
    private String mechanismOfAction;
    private String asorption;
    private String toxicity;
}
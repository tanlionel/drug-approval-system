package com.example.drugapprovalsystem.model.DTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductAllergyDetailDTO {
    private String detail;
    private String summary;
}
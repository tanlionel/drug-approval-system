package com.example.drugapprovalsystem.model.DTO.product_request_dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManuFactorRequestDTO {
    private String name;
    private String company;
    private String score;
    private String source;
    private Integer countryId;
}

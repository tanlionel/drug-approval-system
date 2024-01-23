package com.example.drugapprovalsystem.model.DTO.product_dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ManufactorRequestDTO {
    private String name;
    private String company;
    private String score;
    private String source;
    private Integer countryId;
}

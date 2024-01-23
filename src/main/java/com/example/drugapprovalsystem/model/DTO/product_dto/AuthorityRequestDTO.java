package com.example.drugapprovalsystem.model.DTO.product_dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityRequestDTO {
    private String certificateName;
    private Integer countryId;
}

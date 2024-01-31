package com.example.drugapprovalsystem.model.DTO.product_request_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityRequestDTO {
    private String certificateName;
    private Integer countryId;
}

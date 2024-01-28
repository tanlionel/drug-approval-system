package com.example.drugapprovalsystem.model.DTO.product_request_dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityDTO {
    private String certificateName;
    private Integer countryId;
}

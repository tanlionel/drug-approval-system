package com.example.drugapprovalsystem.model.DTO.product_response_dto;

import com.example.drugapprovalsystem.entity.ApprovalProduct;
import com.example.drugapprovalsystem.entity.Country;
import com.example.drugapprovalsystem.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityResponseDTO {
    private String certificateName;

    private Country country;
}

package com.example.drugapprovalsystem.model.DTO.profile_response_dto;

import com.example.drugapprovalsystem.model.DTO.product_response_dto.ProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ProductResponseDTO;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileProductDTO {
    private int profileDetailId;

    private ProductDetailResponseDTO productResponseDTO;

    private String status;
}

package com.example.drugapprovalsystem.model.DTO.profile_request_dto;

import com.example.drugapprovalsystem.model.DTO.product_request_dto.ProductRequestDTO;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDetailRequestDTO {
    private int profileDetailId;
    private int productId;
    private ProductRequestDTO product;
    private String status;
}

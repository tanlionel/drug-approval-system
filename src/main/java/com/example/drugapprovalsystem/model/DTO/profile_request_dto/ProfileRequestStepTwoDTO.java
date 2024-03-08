package com.example.drugapprovalsystem.model.DTO.profile_request_dto;

import com.example.drugapprovalsystem.model.DTO.product_request_dto.ProductRequestDTO;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequestStepTwoDTO {
    private int profileId;
    private List<ProductRequestDTO> productList;
    private String status;
}

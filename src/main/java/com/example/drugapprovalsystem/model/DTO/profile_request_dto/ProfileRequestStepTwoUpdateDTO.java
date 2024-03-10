package com.example.drugapprovalsystem.model.DTO.profile_request_dto;

import com.example.drugapprovalsystem.model.DTO.product_request_dto.ProductRequestDTO;
import lombok.*;
import org.springframework.data.util.Pair;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequestStepTwoUpdateDTO {
    private int profileId;
    private List<ProfileDetailRequestDTO> productDetailList;
    private String status;
}

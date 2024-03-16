package com.example.drugapprovalsystem.model.DTO.profile_request_dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDetailSubmitRequestDTO {
    private int profileDetailId;
    private String status;
}

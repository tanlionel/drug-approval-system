package com.example.drugapprovalsystem.model.DTO.profile_request_dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequestStepTwoUpdateDTO {
    private int profileId;
    private List<ProfileDetailRequestDTO> profileDetailList;
    private String status;
}

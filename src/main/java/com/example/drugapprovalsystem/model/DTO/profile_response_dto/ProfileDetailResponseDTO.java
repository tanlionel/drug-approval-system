package com.example.drugapprovalsystem.model.DTO.profile_response_dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDetailResponseDTO {

    private ProfileResponseDTO profileInformation;

    List<ProfileProductDTO> profileDetailList;

}

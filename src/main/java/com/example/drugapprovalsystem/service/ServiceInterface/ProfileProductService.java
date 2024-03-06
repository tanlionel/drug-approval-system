package com.example.drugapprovalsystem.service.ServiceInterface;

import com.example.drugapprovalsystem.entity.Profile;
import com.example.drugapprovalsystem.entity.ProfileDetail;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepOneDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepTwoDTO;
import com.example.drugapprovalsystem.model.DTO.profile_response_dto.ProfileResponseDTO;

public interface ProfileProductService {

    public Profile createProfile(ProfileRequestStepOneDTO profileRequestStepOneDTO) throws Exception;

    public ProfileDetail createProfileDetail(ProfileRequestStepTwoDTO profileRequestStepTwoDTO) throws Exception;
}

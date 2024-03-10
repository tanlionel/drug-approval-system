package com.example.drugapprovalsystem.service.ServiceInterface;

import com.example.drugapprovalsystem.entity.Profile;
import com.example.drugapprovalsystem.entity.ProfileDetail;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepOneDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepTwoDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepTwoUpdateDTO;
import com.example.drugapprovalsystem.model.DTO.profile_response_dto.ProfileDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.profile_response_dto.ProfileResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfileProductService {

    public Profile createProfile(ProfileRequestStepOneDTO profileRequestStepOneDTO) throws Exception;

    public void createProfileDetail(ProfileRequestStepTwoDTO profileRequestStepTwoDTO) throws Exception;

    public List<ProfileResponseDTO> getAllProfilesPageable(int pageIndex, int pageSize, String searchKeyword);
    public ProfileDetailResponseDTO getProfileDetails(int profileId) throws Exception;
    public Profile updateProfileDetail(int profileId, ProfileRequestStepOneDTO profileRequestStepOneDTO) throws Exception;

    //For step 2
    public void updateProfileDetail(ProfileRequestStepTwoUpdateDTO profileRequestStepTwoUpdateDTO) throws Exception;
}

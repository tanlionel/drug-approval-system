package com.example.drugapprovalsystem.service.ServiceInterface;

import com.example.drugapprovalsystem.entity.Profile;
import com.example.drugapprovalsystem.exception.ProfileDoesNotExistException;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.*;
import com.example.drugapprovalsystem.model.DTO.profile_response_dto.ProfileDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.profile_response_dto.ProfileResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfileProductService {

    public Profile createProfile(ProfileRequestStepOneDTO profileRequestStepOneDTO) throws Exception;

    public ProfileDetailResponseDTO createProfileDetail(ProfileRequestStepTwoDTO profileRequestStepTwoDTO) throws Exception;
    public Page<ProfileResponseDTO> getAllProfilesPageable(int pageIndex, int pageSize, String searchKeyword) throws Exception;
   public ProfileDetailResponseDTO getProfileDetails(int profileId) throws Exception;
    public Profile updateProfileDetail(int profileId, ProfileRequestStepOneDTO profileRequestStepOneDTO) throws Exception;
    //For step 2
    public ProfileDetailResponseDTO updateProfileDetail(ProfileRequestStepTwoUpdateDTO profileRequestStepTwoUpdateDTO) throws Exception;
    public ProfileDetailResponseDTO createOrUpdateProfileDetail(ProfileRequestStepTwoUpdateDTO profileRequestStepTwoUpdateDTO) throws Exception;
    public ProfileRequestStepTwoUpdateDTO getProfileDetailForUpdate(int profileId) throws Exception;
    Profile uploadImage(int profileId, String s) throws ProfileDoesNotExistException;
    ProfileDetailResponseDTO processingProfile(int profileId) throws Exception;
    ProfileDetailResponseDTO submitProfile(int profileId, List<ProfileDetailSubmitRequestDTO> submitRequestDTO) throws Exception;
}

package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.Product;
import com.example.drugapprovalsystem.entity.Profile;
import com.example.drugapprovalsystem.entity.ProfileDetail;
import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepOneDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepTwoDTO;
import com.example.drugapprovalsystem.model.DTO.profile_response_dto.ProfileResponseDTO;

import java.time.LocalDateTime;

public class ProfileMapper {

    public static Profile mapToProfile (ProfileRequestStepOneDTO p)
    {
        Profile profile;

        profile = Profile.builder()
                .title(p.getTitle())
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .status(p.getStatus())
                .isActive(Common.IS_ACTIVE)
                .build();

        return profile;
    }

    public static ProfileDetail mapToProfileDetail (ProfileRequestStepTwoDTO p)
    {
        ProfileDetail profileDetail;

        profileDetail = ProfileDetail.builder()
                .id(p.getProfileId())
                .product((Product) p.getProductList())
                .status(p.getStatus())
                .createdOn(LocalDateTime.now())
                .build();

        return profileDetail;
    }

}

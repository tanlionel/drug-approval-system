package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.Product;
import com.example.drugapprovalsystem.entity.Profile;
import com.example.drugapprovalsystem.entity.ProfileDetail;
import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.model.DTO.DrugIngredientsDTO;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.AuthorityRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ManuFactorRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ProductResponseDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileDetailRequestDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepOneDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepTwoDTO;
import com.example.drugapprovalsystem.model.DTO.profile_response_dto.ProfileDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.profile_response_dto.ProfileProductDTO;
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
                .image(p.getImageURL())
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

    public static ProfileDetailRequestDTO mapToProfileDetailRequestDTO(ProfileProductDTO dto) {
        ProductDetailResponseDTO p = dto.getProductResponseDTO();

        return
                ProfileDetailRequestDTO.builder()
                        .status(dto.getStatus())
                        .profileDetailId(dto.getProfileDetailId())
                        .productId((p == null) ? null : p.getId())
                        .product(
                                (p == null) ? null :
                                        ProductRequestDTO
                                                .builder()
                                                .labeller(p.getLabeller())
                                                .name(p.getName())
                                                .prescriptionName(p.getPrescriptionName())
                                                .categoryId(p.getCategory().getId())
                                                .route(p.getRoute())
                                                .imageURL(p.getImage())
                                                .authorities(
                                                        (p.getAuthorities() == null) ? null :
                                                        p.getAuthorities().stream()
                                                                .map(authorityDTO ->
                                                                        AuthorityRequestDTO.builder()
                                                                                .certificateName(authorityDTO.getCertificateName())
                                                                                .countryId(authorityDTO.getCountryId())
                                                                                .build()
                                                                        ).toList()
                                                )
                                                .drugIngredients(
                                                        p.getDrugIngredients() == null ? null :
                                                                p.getDrugIngredients().stream()
                                                                        .map(d ->
                                                                                DrugIngredientsDTO.builder()
                                                                                        .drugId(d.getDrugId())
                                                                                        .strengthUnit(d.getStrengthUnit())
                                                                                        .strengthNumber(d.getStrengthNumber())
                                                                                        .strength(d.getStrength())
                                                                                        .clinicallyRelevant(d.getClinicallyRelevant())
                                                                                        .build()
                                                                        ).toList()
                                                )
                                                .contraindication(p.getContraindication())
                                                .pharmacogenomic(p.getPharmacogenomic())
                                                .productAllergyDetail(p.getProductAllergyDetail())
                                                .manufactor(
                                                        p.getManufactor() == null ? null :
                                                        ManuFactorRequestDTO.builder()
                                                                .company(p.getManufactor().getCompany())
                                                                .name(p.getManufactor().getName())
                                                                .score(p.getManufactor().getScore())
                                                                .countryId(p.getManufactor().getCountryId())
                                                                .source(p.getManufactor().getSource())
                                                                .build()
                                                )
                                                .isApprovedByANSM(p.isApprovedByANSM())
                                                .isApprovedByFDA(p.isApprovedByFDA())
                                                .build()
                        )
                        .build();

    }
}

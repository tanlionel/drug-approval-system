package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.Product;
import com.example.drugapprovalsystem.entity.Profile;
import com.example.drugapprovalsystem.entity.ProfileDetail;
import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.exception.ProfileDoesNotExistException;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.*;
import com.example.drugapprovalsystem.model.DTO.profile_response_dto.ProfileDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.profile_response_dto.ProfileProductDTO;
import com.example.drugapprovalsystem.model.DTO.profile_response_dto.ProfileResponseDTO;
import com.example.drugapprovalsystem.model.Mapper.ProductMapper;
import com.example.drugapprovalsystem.model.Mapper.ProfileMapper;
import com.example.drugapprovalsystem.repository.ProductRepository;
import com.example.drugapprovalsystem.repository.ProfileDetailRepository;
import com.example.drugapprovalsystem.repository.ProfileProductRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.PageableService;
import com.example.drugapprovalsystem.service.ServiceInterface.ProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.ProfileProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileProductServiceImplement implements ProfileProductService {
    @Autowired
    ProfileProductRepository profileProductRepository;
    @Autowired
    ProfileDetailRepository profileDetailRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    PageableService pageableService;
    //STATUS
    private final String PROFILE_PROCESSING = "PROCESSING";
    private final String PROFILE_DETAIL_APPROVED_BY_SYSTEM = "APPROVED BY SYSTEM";
    private final String PROFILE_DETAIL_REJECTED_BY_SYSTEM = "REJECTED BY SYSTEM";
    private final String PROFILE_CLOSED = "CLOSED";
    private final String PROFILE_DETAIL_REJECTED_BY_ADMIN = "REJECTED";
    private final String PROFILE_DETAIL_APPROVED_BY_ADMIN = "APPROVED";
    @Override
    public Profile createProfile(ProfileRequestStepOneDTO profileRequestStepOneDTO) throws Exception {
        Profile profile = ProfileMapper.mapToProfile(profileRequestStepOneDTO);

        profile.setCreatedBy(userService.getLoginUser());
        profile.setUpdatedBy(userService.getLoginUser());

        Profile result = profileProductRepository.save(profile);
        return result;
    }

    @Override
    public void createProfileDetail(ProfileRequestStepTwoDTO profileRequestStepTwoDTO) throws Exception {
        Integer profileId = profileRequestStepTwoDTO.getProfileId();
        List<ProductRequestDTO> productRequestDTOList = profileRequestStepTwoDTO.getProductList();

        List<ProductDetailResponseDTO> productDetailResponseDTOList = productRequestDTOList.stream().map(p -> {
            try {
                return productService.createProduct(p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();

        List<ProfileDetail> profileDetailList = productDetailResponseDTOList.stream().map(p -> ProfileDetail.builder()
                .profile(Profile.builder().id(profileId).build())
                .product(Product.builder().id(p.getId()).build())
                .status(profileRequestStepTwoDTO.getStatus())
                .createdOn(LocalDateTime.now())
                .isActive(Common.IS_ACTIVE)
                .build()
        ).toList();

        profileDetailRepository.saveAll(profileDetailList);

    }

    @Override
    public Page<ProfileResponseDTO> getAllProfilesPageable(int pageIndex,
                                                           int pageSize,
                                                           String searchKeyword) {

        Pageable pageable = pageableService.getPageable(pageIndex, pageSize);

        Page<Profile> profilePage = profileProductRepository.findAllByTitleContainingAndIsActive(pageable,
                searchKeyword,
                Common.IS_ACTIVE);

        Page<ProfileResponseDTO> resultPage = new PageImpl<ProfileResponseDTO>(profilePage
                .stream().map(p -> ProfileResponseDTO.builder()
                        .title(p.getTitle())
                        .profileId(p.getId())
                        .createdBy((p.getCreatedBy() != null) ? p.getCreatedBy().getUsername() : null)
                        .updatedBy((p.getUpdatedBy() == null) ? null : p.getUpdatedBy().getUsername())
                        .updatedOn(p.getUpdatedOn())
                        .createdOn(p.getCreatedOn())
                        .status(p.getStatus())
                        .imageURL(p.getImage())
                        .build()
                ).toList(), pageable, profilePage.getTotalElements());

        return resultPage;
    }

    @Override
    public ProfileDetailResponseDTO getProfileDetails(int profileId) throws Exception {
        ProfileDetailRequestDTO result;

        Profile profile = profileProductRepository.findByIdAndIsActive(profileId, Common.IS_ACTIVE);

        if (profile == null)
            throw new ProfileDoesNotExistException();

        ProfileResponseDTO profileResponseDTO = ProfileResponseDTO.builder()
                .profileId(profile.getId())
                .title(profile.getTitle())
                .updatedBy(profile.getUpdatedBy() == null ? null : profile.getUpdatedBy().getUsername())
                .updatedOn(profile.getUpdatedOn())
                .createdOn(profile.getCreatedOn())
                .createdBy(profile.getCreatedBy() == null ? null : profile.getCreatedBy().getUsername())
                .imageURL(profile.getImage())
                .status(profile.getStatus()).build();

        List<ProfileProductDTO> profileDetailList;

        try {
            profileDetailList = profileDetailRepository.findAllByProfileIdAndIsActive(profileId, Common.IS_ACTIVE)
                    .stream().map(p -> ProfileProductDTO.builder()
                            .profileDetailId(p.getId())
                            .productResponseDTO(
                                    (p.getProduct() == null ? null : ProductMapper.mapToProductResponseDTO(p.getProduct()))
                            )
                            .status(p.getStatus())
                                    .build()
                            ).toList();
        }
        catch (Exception e) {
            profileDetailList = null;
        }

        return ProfileDetailResponseDTO.builder()
                .profileInformation(profileResponseDTO)
                .profileDetailList(profileDetailList)
                .build();
    }

    @Override
    public Profile updateProfileDetail(int profileId, ProfileRequestStepOneDTO profileRequestStepOneDTO) throws Exception {
        User loginUser = userService.getLoginUser();

        Profile profile = Profile.builder().id(profileId)
                .status(profileRequestStepOneDTO.getStatus())
                .updatedBy(loginUser)
                .updatedOn(LocalDateTime.now())
                .title(profileRequestStepOneDTO.getTitle())
                .image(profileRequestStepOneDTO.getImageURL())
                .isActive(true)
                .build();

        return profileProductRepository.save(profile);
    }

    @Override
    public void updateProfileDetail(ProfileRequestStepTwoUpdateDTO profileRequestStepTwoUpdateDTO) throws Exception {

        int profileId = profileRequestStepTwoUpdateDTO.getProfileId();

        //Deactive the deleted valued of profile detail
        List<Integer> profileDetailIdList = profileRequestStepTwoUpdateDTO.getProductDetailList()
                .stream()
                .map(p -> p.getProfileDetailId())
                .toList();

        profileDetailRepository.updateActiveProfileDetailNotInIdListByProfileId(
                !Common.IS_ACTIVE,
                profileDetailIdList,
                profileId
        );

        List<ProductDetailResponseDTO> productDetailResponseDTOList = profileRequestStepTwoUpdateDTO.getProductDetailList().stream().map(p -> {
            try {
                return productService.updateProduct(
                        p.getProductId(), p.getProduct()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();

        List<ProfileDetail> profileDetailList = profileRequestStepTwoUpdateDTO.getProductDetailList().stream().map(p -> ProfileDetail.builder()
                .id(p.getProfileDetailId())
                .profile(Profile.builder().id(profileId).build())
                .product(Product.builder().id(p.getProductId()).build())
                .status(p.getStatus())
                .isActive(Common.IS_ACTIVE)
                .build()
        ).toList();

        profileDetailRepository.saveAll(profileDetailList);
    }

    @Override
    public Profile uploadImage(int profileId, String s) throws ProfileDoesNotExistException {
        Profile profile = profileProductRepository.findByIdAndIsActive(profileId,Common.IS_ACTIVE);
        if (profile == null) throw new ProfileDoesNotExistException();
        profile.setImage(s);
        return profileProductRepository.save(profile);
    }

    @Override
    public ProfileDetailResponseDTO processingProfile(int profileId) throws Exception {
        List<ProfileDetail> profileDetails = profileDetailRepository
                .findAllByProfileIdAndIsActive(profileId, Common.IS_ACTIVE);

        boolean flag = false;

        for (ProfileDetail p : profileDetails) {
            Product product = p.getProduct();

            if (product != null && product.isApprovedByANSM() && product.isApprovedByFDA()) {
                flag = true;

                profileDetailRepository.updateProfileDetailStatusById(p.getId(),
                        PROFILE_DETAIL_APPROVED_BY_SYSTEM);
            }
            else {
                profileDetailRepository.updateProfileDetailStatusById(p.getId(),
                                    PROFILE_DETAIL_REJECTED_BY_SYSTEM);
            }
        }

        //If there is no product to approve ==> Profile is closed without submit
        profileProductRepository.updateProfileStatus(profileId,
                flag ? PROFILE_PROCESSING : PROFILE_CLOSED);

        return getProfileDetails(profileId);
    }

    @Override
    public ProfileDetailResponseDTO submitProfile(int profileId, List<ProfileSubmitRequestDTO> submitRequestDTO) throws Exception {
        for (ProfileSubmitRequestDTO s : submitRequestDTO) {

                profileDetailRepository.updateProfileDetailStatusById(s.getProfileId(),
                        s.getStatus() == null ? PROFILE_DETAIL_REJECTED_BY_ADMIN : s.getStatus());

        }

        profileProductRepository.updateProfileStatus(profileId, PROFILE_CLOSED);
        return getProfileDetails(profileId);
    }

}

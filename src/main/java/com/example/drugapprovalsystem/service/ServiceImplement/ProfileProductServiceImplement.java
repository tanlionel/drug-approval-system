package com.example.drugapprovalsystem.service.ServiceImplement;

import com.amazonaws.services.simpleworkflow.flow.core.TryCatch;
import com.example.drugapprovalsystem.entity.Product;
import com.example.drugapprovalsystem.entity.Profile;
import com.example.drugapprovalsystem.entity.ProfileDetail;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ProductResponseDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepOneDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepTwoDTO;
import com.example.drugapprovalsystem.model.Mapper.ProductMapper;
import com.example.drugapprovalsystem.model.Mapper.ProfileMapper;
import com.example.drugapprovalsystem.repository.ProductRepository;
import com.example.drugapprovalsystem.repository.ProfileDetailRepository;
import com.example.drugapprovalsystem.repository.ProfileProductRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.ProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.ProfileProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Override
    public Profile createProfile(ProfileRequestStepOneDTO profileRequestStepOneDTO) throws Exception {

        Profile profile = ProfileMapper.mapToProfile(profileRequestStepOneDTO);
        profile.setCreatedBy(userService.getLoginUser());
        profile.setCreatedBy(userService.getLoginUser());
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
                .build()
        ).toList();

        profileDetailRepository.saveAll(profileDetailList);

    }

}

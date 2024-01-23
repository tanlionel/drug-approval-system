package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.entity.*;
import com.example.drugapprovalsystem.model.DTO.product_dto.ApprovalProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_dto.ApprovalProductResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_dto.PharmacogenomicRequestDTO;
import com.example.drugapprovalsystem.model.Mapper.AuthorityMapper;
import com.example.drugapprovalsystem.model.Mapper.IngredientMapper;
import com.example.drugapprovalsystem.model.Mapper.ProductMapper;
import com.example.drugapprovalsystem.repository.*;
import com.example.drugapprovalsystem.service.ServiceInterface.ApprovalProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.PageableService;
import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.Configuration;
import java.util.List;

@Service
public class ApprovalProductServiceImplement implements ApprovalProductService {
    private final String SORT_ASC = "ASC";
    private final boolean IS_APPROVAL_PRODUCT = true;
    @Autowired
    UserService userService;
    @Autowired
    PageableService pageableService;
    @Autowired
    ApprovalProductRepository approvalProductRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    ManufactorRepository manufactorRepository;
    @Autowired
    LabellerRepository labellerRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    ContraindicationRepository contraindicationRepository;
    @Autowired
    ProductAllergyDetailRepository productAllergyDetailRepository;
    @Autowired
    PharmacogenomicRepository pharmacogenomicRepository;
    @Override
    public Page<ApprovalProductResponseDTO> getPageableApprovalProduct(int pageNo, int pageSize,
                                                                       String sortField, String sortOrder,
                                                                       String search) {
        Pageable pageable = pageableService.getPageableWithSort(pageNo, pageSize, sortField, sortOrder);

        Page<ApprovalProduct> page = approvalProductRepository.findAllByNameContaining(pageable, search);

        return page.map(ProductMapper::mapToApprovalProductResponseDTO);
    }

    @Override
    public void createApprovalProduct(ApprovalProductRequestDTO approvalProductRequestDTO) {
        ApprovalProduct approvalProduct = ProductMapper.mapToApprovalProduct(approvalProductRequestDTO);

        //Optional DETAILS
        if (approvalProduct.getPharmacogenomic() != null)
            approvalProduct.setPharmacogenomic(pharmacogenomicRepository.save(approvalProduct.getPharmacogenomic()));
        if (approvalProduct.getProductAllergyDetails() != null)
            approvalProduct.setProductAllergyDetails(productAllergyDetailRepository.save(approvalProduct.getProductAllergyDetails()));
        if (approvalProduct.getContraindication() != null)
            approvalProduct.setContraindication(contraindicationRepository.save(approvalProduct.getContraindication()));
        //

        //Save the manufactor first
        approvalProduct.setManufactor(manufactorRepository.save(approvalProduct.getManufactor()));
        //Save the labeller second
        approvalProduct.setLabeller(labellerRepository.save(approvalProduct.getLabeller()));
        //Set Create by
        approvalProduct.setCreatedBy(userService.getLoginUser());
        //Save approval product
        ApprovalProduct result = approvalProductRepository.save(approvalProduct);

        List<Ingredient> ingredientList = null;
        List<Authority> authorityList = null;
        //SAVE INGREDIENT AND AUTHORITY LIST
        if (result != null) {
            ingredientList = approvalProductRequestDTO.getDrugIngredients()
                    .stream()
                    .map(ingredient -> IngredientMapper.mapToIngredient(ingredient, result.getId(), IS_APPROVAL_PRODUCT))
                    .toList();

            authorityList = approvalProductRequestDTO.getAuthorities()
                    .stream()
                    .map(authorityRequestDTO -> AuthorityMapper.mapToAuthority(authorityRequestDTO, result.getId(), IS_APPROVAL_PRODUCT))
                    .toList();
        }

        if (ingredientList != null)
            ingredientRepository.saveAll(ingredientList);
        if (authorityList != null)
            authorityRepository.saveAll(authorityList);
    }

}

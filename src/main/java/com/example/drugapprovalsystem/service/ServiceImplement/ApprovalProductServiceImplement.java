package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.entity.*;
import com.example.drugapprovalsystem.exception.InvalidActionException;
import com.example.drugapprovalsystem.exception.ProductDoesNotExistException;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ApprovalProductDetailDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductResponseDTO;
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

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
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
    public ApprovalProductDetailResponseDTO createApprovalProduct(ApprovalProductDetailDTO approvalProductDetailDTO) throws Exception {
        ApprovalProduct approvalProduct = ProductMapper.mapToApprovalProduct(approvalProductDetailDTO);

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
        ingredientList = approvalProductDetailDTO.getDrugIngredients()
                .stream()
                .map(ingredient -> IngredientMapper.mapToIngredient(ingredient, result.getId(), IS_APPROVAL_PRODUCT))
                .toList();

        authorityList = approvalProductDetailDTO.getAuthorities()
                .stream()
                .map(authorityRequestDTO -> AuthorityMapper.mapToAuthority(authorityRequestDTO, result.getId(), IS_APPROVAL_PRODUCT))
                .toList();

        ingredientRepository.saveAll(ingredientList);

        authorityRepository.saveAll(authorityList);

        return getApprovalProductDetail(result.getId());
    }

    @Override
    public ApprovalProductDetailResponseDTO updateApprovalProduct(Integer id, ApprovalProductDetailDTO approvalProductDetailDTO) throws Exception {
        approvalProductDetailDTO.setId(id);

        if (approvalProductDetailDTO.getId() == null)
            throw new ProductDoesNotExistException();

        ApprovalProduct approvalProduct = ProductMapper.mapToApprovalProduct(approvalProductDetailDTO);

        Optional<ApprovalProduct> optional = approvalProductRepository.findById(approvalProductDetailDTO.getId());

        if (optional.isEmpty())
            throw new ProductDoesNotExistException();

        ApprovalProduct approvalProductInDB = optional.get();

        int labelId = approvalProductInDB.getLabeller() == null ? -1 : approvalProductInDB.getLabeller().getId(),
                pharmacogenomicId = approvalProductInDB.getPharmacogenomic() == null ? -1 : approvalProductInDB.getPharmacogenomic().getId(),
                productAllergyDetailId = approvalProductInDB.getProductAllergyDetails() == null ? -1 : approvalProductInDB.getProductAllergyDetails().getId(),
                contraindicationId = approvalProductInDB.getContraindication() == null ? -1 : approvalProductInDB.getContraindication().getId(),
                manufactorId = approvalProductInDB.getManufactor() == null ? -1 : approvalProductInDB.getManufactor().getId();

        //Optional DETAILS
        if (approvalProduct.getPharmacogenomic() != null)
            approvalProduct.setPharmacogenomic(pharmacogenomicRepository.save(approvalProduct.getPharmacogenomic()));

        if (approvalProduct.getProductAllergyDetails() != null)
            approvalProduct.setProductAllergyDetails(productAllergyDetailRepository.save(approvalProduct.getProductAllergyDetails()));

        if (approvalProduct.getContraindication() != null)
            approvalProduct.setContraindication(contraindicationRepository.save(approvalProduct.getContraindication()));
        //

        //Save the manufactor first
        if (approvalProduct.getManufactor() != null)
            approvalProduct.setManufactor(manufactorRepository.save(approvalProduct.getManufactor()));

        //Save the labeller second
        if (approvalProduct.getLabeller() != null)
            approvalProduct.setLabeller(labellerRepository.save(approvalProduct.getLabeller()));

        //Set Create by
        approvalProduct.setCreatedBy(userService.getLoginUser());
        //Save approval product
        ApprovalProduct result = approvalProductRepository.save(approvalProduct);

        List<Ingredient> ingredientList = null;
        List<Authority> authorityList = null;

        //SAVE INGREDIENT AND AUTHORITY LIST
        ingredientList = approvalProductDetailDTO.getDrugIngredients()
                    .stream()
                    .map(ingredient -> IngredientMapper.mapToIngredient(ingredient, result.getId(), IS_APPROVAL_PRODUCT))
                    .toList();

        authorityList = approvalProductDetailDTO.getAuthorities()
                    .stream()
                    .map(authorityRequestDTO -> AuthorityMapper.mapToAuthority(authorityRequestDTO, result.getId(), IS_APPROVAL_PRODUCT))
                    .toList();

        //DELETE BEFORE SAVE
        ingredientRepository.deleteByApprovalProductId(approvalProduct.getId());
        authorityRepository.deleteByApprovalProductId(approvalProduct.getId());

        if (pharmacogenomicId > -1)
            pharmacogenomicRepository.deleteById(pharmacogenomicId);
        if (productAllergyDetailId > -1)
            productAllergyDetailRepository.deleteById(productAllergyDetailId);
        if (contraindicationId > -1)
            contraindicationRepository.deleteById(contraindicationId);
        if (manufactorId > -1)
            manufactorRepository.deleteById(manufactorId);
        if (labelId > -1)
            labellerRepository.deleteById(labelId);

        ingredientRepository.saveAll(ingredientList);
        authorityRepository.saveAll(authorityList);

        return getApprovalProductDetail(id);
    }

    @Override
    public void deleteApprovalProduct(Integer id) throws Exception {
        Optional<ApprovalProduct> optional = approvalProductRepository.findById(id);

        if (optional.isEmpty())
            throw new ProductDoesNotExistException();

        ApprovalProduct approvalProductInDB = optional.get();

        if (!approvalProductInDB.getIsActive())
            throw new InvalidActionException();

        approvalProductInDB.setIsActive(!approvalProductInDB.getIsActive());
        approvalProductRepository.save(approvalProductInDB);
    }

    @Override
    public void activeApproveProduct(Integer id) throws Exception {
        Optional<ApprovalProduct> optional = approvalProductRepository.findById(id);

        if (optional.isEmpty())
            throw new ProductDoesNotExistException();

        ApprovalProduct approvalProductInDB = optional.get();

        if (approvalProductInDB.getIsActive())
            throw new InvalidActionException();

        approvalProductInDB.setIsActive(!approvalProductInDB.getIsActive());
        approvalProductRepository.save(approvalProductInDB);
    }

    @Override
    public ApprovalProductDetailResponseDTO getApprovalProductDetail(Integer id) throws Exception {
        Optional<ApprovalProduct> optional = approvalProductRepository.findById(id);

        if (optional.isEmpty())
            throw new ProductDoesNotExistException();

        ApprovalProduct approvalProduct = optional.get();
        List<Ingredient> ingredients = null;
        List<Authority> authorities = null;

        try {
            ingredients = ingredientRepository.findByApprovalProductId(id).stream().toList();
        }
        finally {
        }

        try {
            authorities = authorityRepository.findByApprovalProductId(id).stream().toList();
        }
        finally {
        }

        return ProductMapper
                .mapToApprovalProductDetaiResponseDTO(approvalProduct, ingredients, authorities);
    }

}

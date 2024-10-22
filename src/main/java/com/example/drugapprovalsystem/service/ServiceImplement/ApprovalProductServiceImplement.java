package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.entity.*;
import com.example.drugapprovalsystem.enums.ProductAdministrationEnum;
import com.example.drugapprovalsystem.exception.InvalidActionException;
import com.example.drugapprovalsystem.exception.ProductDoesNotExistException;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ApprovalProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductResponseDTO;
import com.example.drugapprovalsystem.model.Mapper.AuthorityMapper;
import com.example.drugapprovalsystem.model.Mapper.IngredientMapper;
import com.example.drugapprovalsystem.model.Mapper.ProductMapper;
import com.example.drugapprovalsystem.repository.*;
import com.example.drugapprovalsystem.service.ServiceInterface.ApprovalProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.PageableService;
import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
    public Page<ApprovalProductResponseDTO> getPageableApprovalProductByFDA(int pageNo, int pageSize, String sortField, String sortOrder, String search) {
        Pageable pageable = pageableService.getPageableWithSort(pageNo, pageSize, sortField, sortOrder);

        Page<ApprovalProduct> page = approvalProductRepository.findAllByNameContainingAndProductAdministrationId(pageable, search, 1);

        return page.map(ProductMapper::mapToApprovalProductResponseDTO);
    }

    @Override
    public Page<ApprovalProductResponseDTO> getPageableApprovalProductByANSM(int pageNo, int pageSize, String sortField, String sortOrder, String search) {
        Pageable pageable = pageableService.getPageableWithSort(pageNo, pageSize, sortField, sortOrder);

        Page<ApprovalProduct> page = approvalProductRepository.findAllByNameContainingAndProductAdministrationId(pageable, search, 2);

        return page.map(ProductMapper::mapToApprovalProductResponseDTO);
    }

    @Override
    public Page<ApprovalProductResponseDTO> getPageableApprovalProductByDAV(int pageNo, int pageSize, String sortField, String sortOrder, String search) {
        Pageable pageable = pageableService.getPageableWithSort(pageNo, pageSize, sortField, sortOrder);

        Page<ApprovalProduct> page = approvalProductRepository.findAllByNameContainingAndProductAdministrationId(pageable, search,3);

        return page.map(ProductMapper::mapToApprovalProductResponseDTO);
    }

    @Override
    public Page<ApprovalProductResponseDTO> getPageableApprovalProduct(int pageNo, int pageSize,
                                                                       String sortField, String sortOrder,
                                                                       String search) {
        Pageable pageable = pageableService.getPageableWithSort(pageNo, pageSize, sortField, sortOrder);

        Page<ApprovalProduct> page = approvalProductRepository.findAllByNameContaining(pageable, search);

        return page.map(ProductMapper::mapToApprovalProductResponseDTO);
    }

    @Override
    public ApprovalProductDetailResponseDTO createApprovalProduct(ApprovalProductRequestDTO approvalProductRequestDTO) throws Exception {
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

        List<Ingredient> ingredientList;
        List<Authority> authorityList;
        //SAVE INGREDIENT AND AUTHORITY LIST
        ingredientList = approvalProductRequestDTO.getDrugIngredients()
                .stream()
                .map(ingredient -> IngredientMapper.mapToIngredient(ingredient, result.getId(), IS_APPROVAL_PRODUCT))
                .toList();

        authorityList = approvalProductRequestDTO.getAuthorities()
                .stream()
                .map(authorityRequestDTO -> AuthorityMapper.mapToAuthority(authorityRequestDTO, result.getId(), IS_APPROVAL_PRODUCT))
                .toList();

        ingredientRepository.saveAll(ingredientList);

        authorityRepository.saveAll(authorityList);

        return getApprovalProductDetail(result.getId());
    }

    @Override
    public ApprovalProductDetailResponseDTO updateApprovalProduct(Integer id, ApprovalProductRequestDTO approvalProductRequestDTO) throws Exception {
        if (id == null)
            throw new ProductDoesNotExistException();

        ApprovalProduct approvalProduct = ProductMapper.mapToApprovalProduct(approvalProductRequestDTO);
        approvalProduct.setId(id);

        Optional<ApprovalProduct> optional = approvalProductRepository.findById(id);

        if (optional.isEmpty())
            throw new ProductDoesNotExistException();

        ApprovalProduct approvalProductInDB = optional.get();

        //GetID
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

        List<Ingredient> ingredientList;
        List<Authority> authorityList;

        //SAVE INGREDIENT AND AUTHORITY LIST
        ingredientList = approvalProductRequestDTO.getDrugIngredients()
                    .stream()
                    .map(ingredient -> IngredientMapper.mapToIngredient(ingredient, result.getId(), IS_APPROVAL_PRODUCT))
                    .toList();

        authorityList = approvalProductRequestDTO.getAuthorities()
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
        List<Ingredient> ingredients;
        List<Authority> authorities;
        ingredients = ingredientRepository.findByApprovalProductId(id).stream().toList();

        authorities = authorityRepository.findByApprovalProductId(id).stream().toList();

        return ProductMapper
                .mapToApprovalProductDetaiResponseDTO(approvalProduct, ingredients, authorities);
    }

    @Override
    public ApprovalProductResponseDTO uploadImage(Integer approvalProductID, String imageLink) throws ProductDoesNotExistException {
        Optional<ApprovalProduct> approvalProduct = approvalProductRepository.findById(approvalProductID);
        if (!approvalProduct.isPresent()) throw new ProductDoesNotExistException();
        approvalProduct.get().setImage(imageLink);
        return ProductMapper.mapToApprovalProductResponseDTO(approvalProductRepository.save(approvalProduct.get()));
    }

}

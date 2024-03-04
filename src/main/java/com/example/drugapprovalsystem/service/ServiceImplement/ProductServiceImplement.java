package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.entity.Authority;
import com.example.drugapprovalsystem.entity.Ingredient;
import com.example.drugapprovalsystem.entity.Product;
import com.example.drugapprovalsystem.exception.ProductDoesNotExistException;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ApprovalProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.Mapper.AuthorityMapper;
import com.example.drugapprovalsystem.model.Mapper.IngredientMapper;
import com.example.drugapprovalsystem.model.Mapper.ProductMapper;
import com.example.drugapprovalsystem.repository.*;
import com.example.drugapprovalsystem.service.ServiceInterface.PageableService;
import com.example.drugapprovalsystem.service.ServiceInterface.ProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplement implements ProductService {
    private final String SORT_ASC = "ASC";
    private final boolean IS_APPROVAL_PRODUCT = true;
    @Autowired
    UserService userService;
    @Autowired
    PageableService pageableService;
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
    @Autowired
    ProductRepository productRepository;
    @Override
    public ProductDetailResponseDTO createProduct(ProductRequestDTO productRequestDTO) throws Exception {
        Product product = ProductMapper.mapToProduct(productRequestDTO);

        //Optional DETAILS
        if (product.getPharmacogenomic() != null)
            product.setPharmacogenomic(pharmacogenomicRepository.save(product.getPharmacogenomic()));
        if (product.getProductAllergyDetails() != null)
            product.setProductAllergyDetails(productAllergyDetailRepository.save(product.getProductAllergyDetails()));
        if (product.getContraindication() != null)
            product.setContraindication(contraindicationRepository.save(product.getContraindication()));
        //

        //Save the manufactor first
        product.setManufactor(manufactorRepository.save(product.getManufactor()));
        //Save the labeller second
        product.setLabeller(labellerRepository.save(product.getLabeller()));
        //Set Create by
        product.setCreatedBy(userService.getLoginUser());
        //Save product
        Product result = productRepository.save(product);

        List<Ingredient> ingredientList = null;
        List<Authority> authorityList = null;
        //SAVE INGREDIENT AND AUTHORITY LIST
        ingredientList = productRequestDTO.getDrugIngredients()
                .stream()
                .map(ingredient -> IngredientMapper.mapToIngredient(ingredient, result.getId(), !IS_APPROVAL_PRODUCT))
                .toList();

        authorityList = productRequestDTO.getAuthorities()
                .stream()
                .map(authorityRequestDTO -> AuthorityMapper.mapToAuthority(authorityRequestDTO, result.getId(), !IS_APPROVAL_PRODUCT))
                .toList();

        ingredientRepository.saveAll(ingredientList);

        authorityRepository.saveAll(authorityList);

        return getProductDetail(result.getId());
    }

    //GET DETAIL
    public ProductDetailResponseDTO getProductDetail(Integer id) throws Exception {
        Optional<Product> optional = productRepository.findById(id);

        if (optional.isEmpty())
            throw new ProductDoesNotExistException();

        Product product = optional.get();
        List<Ingredient> ingredients = null;
        List<Authority> authorities = null;

        try {
            ingredients = ingredientRepository.findByProductId(id).stream().toList();
        }
        finally {
        }

        try {
            authorities = authorityRepository.findByProductId(id).stream().toList();
        }
        finally {
        }

        return ProductMapper
                .mapToProductDetaiResponseDTO(product, ingredients, authorities);
    }

    @Override
    public ProductDetailResponseDTO updateProduct(Integer id, ProductRequestDTO productRequestDTO) throws Exception {
        if (id == null)
            throw new ProductDoesNotExistException();

        Product product = ProductMapper.mapToProduct(productRequestDTO);
        product.setId(id);

        Optional<Product> optional = productRepository.findById(id);

        if (optional.isEmpty())
            throw new ProductDoesNotExistException();

        Product productInDB = optional.get();

        //GetID
        int labelId = productInDB.getLabeller() == null ? -1 : productInDB.getLabeller().getId(),
                pharmacogenomicId = productInDB.getPharmacogenomic() == null ? -1 : productInDB.getPharmacogenomic().getId(),
                productAllergyDetailId = productInDB.getProductAllergyDetails() == null ? -1 : productInDB.getProductAllergyDetails().getId(),
                contraindicationId = productInDB.getContraindication() == null ? -1 : productInDB.getContraindication().getId(),
                manufactorId = productInDB.getManufactor() == null ? -1 : productInDB.getManufactor().getId();

        //Optional DETAILS
        if (product.getPharmacogenomic() != null)
            product.setPharmacogenomic(pharmacogenomicRepository.save(product.getPharmacogenomic()));

        if (product.getProductAllergyDetails() != null)
            product.setProductAllergyDetails(productAllergyDetailRepository.save(product.getProductAllergyDetails()));

        if (product.getContraindication() != null)
            product.setContraindication(contraindicationRepository.save(product.getContraindication()));
        //

        //Save the manufactor first
        if (product.getManufactor() != null)
            product.setManufactor(manufactorRepository.save(product.getManufactor()));

        //Save the labeller second
        if (product.getLabeller() != null)
            product.setLabeller(labellerRepository.save(product.getLabeller()));

        //Set Create by
        product.setCreatedBy(userService.getLoginUser());
        //Save product
        Product result = productRepository.save(product);

        List<Ingredient> ingredientList = null;
        List<Authority> authorityList = null;

        //SAVE INGREDIENT AND AUTHORITY LIST
        ingredientList = productRequestDTO.getDrugIngredients()
                .stream()
                .map(ingredient -> IngredientMapper.mapToIngredient(ingredient, result.getId(), !IS_APPROVAL_PRODUCT))
                .toList();

        authorityList = productRequestDTO.getAuthorities()
                .stream()
                .map(authorityRequestDTO -> AuthorityMapper.mapToAuthority(authorityRequestDTO, result.getId(), !IS_APPROVAL_PRODUCT))
                .toList();

        //DELETE BEFORE SAVE
        ingredientRepository.deleteByProductId(product.getId());
        authorityRepository.deleteByProductId(product.getId());

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

        return getProductDetail(id);
    }
}

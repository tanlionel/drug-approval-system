package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.*;
import com.example.drugapprovalsystem.model.DTO.*;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.*;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class ProductMapper {
    public static ApprovalProductResponseDTO mapToApprovalProductResponseDTO(ApprovalProduct a) {
        return ApprovalProductResponseDTO.builder().id(a.getId())
                .labeller((a.getLabeller() == null) ? null : a.getLabeller().getName())
                .name(a.getName())
                .route(a.getRoute())
                .prescriptionName(a.getPrescriptionName())
                .createdOn(a.getCreatedOn())
                .company((a.getManufactor() == null) ? null : a.getManufactor().getCompany())
                .category((a.getCategory() == null) ? null : a.getCategory().getTitle())
                .build();
    }

    public static Product mapToProduct(ProductRequestDTO a) {
        Product product = new Product();

        product = Product.builder()
                .name(a.getName())
                .route(a.getRoute())
                .prescriptionName(a.getPrescriptionName())
                .createdOn(LocalDateTime.now())
                .category(Category.builder().id(a.getCategoryId()).build())
                .labeller(Labeller.builder().name(a.getLabeller()).build())
                .manufactor((a.getManufactor() == null) ? null : Manufactor.builder().name(a.getManufactor().getName())
                        .source(a.getManufactor().getSource())
                        .score(a.getManufactor().getScore())
                        .company(a.getManufactor().getCompany())
                        .country(Country.builder().id(a.getManufactor().getCountryId()).build()).build())
                .pharmacogenomic(a.getPharmacogenomic() == null ? null : Pharmacogenomic.builder()
                        .asorption(a.getPharmacogenomic().getAsorption())
                        .toxicity(a.getPharmacogenomic().getToxicity())
                        .indication(a.getPharmacogenomic().getIndication())
                        .mechanismOfAction(a.getPharmacogenomic().getMechanismOfAction())
                        .pharmacodynamic(a.getPharmacogenomic().getPharmacodynamic()).build())
                .productAllergyDetails(a.getProductAllergyDetail() == null ? null : ProductAllergyDetail.builder()
                        .detail(a.getProductAllergyDetail().getDetail())
                        .summary(a.getProductAllergyDetail().getSummary()).build())
                .contraindication(a.getContraindication() == null ? null : Contraindication.builder()
                        .value(a.getContraindication().getValue())
                        .relationship(a.getContraindication().getRelationship()).build())
                .isActive(Common.IS_ACTIVE).build();

        return product;
    }

    public static ApprovalProduct mapToApprovalProduct(ProductRequestDTO a) {
        ApprovalProduct approvalProduct = new ApprovalProduct();

        approvalProduct = ApprovalProduct.builder()
                .name(a.getName())
                .route(a.getRoute())
                .prescriptionName(a.getPrescriptionName())
                .createdOn(LocalDateTime.now())
                .category(Category.builder().id(a.getCategoryId()).build())
                .labeller(Labeller.builder().name(a.getLabeller()).build())
                .manufactor((a.getManufactor() == null) ? null : Manufactor.builder().name(a.getManufactor().getName())
                        .source(a.getManufactor().getSource())
                        .score(a.getManufactor().getScore())
                        .company(a.getManufactor().getCompany())
                        .country(Country.builder().id(a.getManufactor().getCountryId()).build()).build())
                .pharmacogenomic(a.getPharmacogenomic() == null ? null : Pharmacogenomic.builder()
                        .asorption(a.getPharmacogenomic().getAsorption())
                        .toxicity(a.getPharmacogenomic().getToxicity())
                        .indication(a.getPharmacogenomic().getIndication())
                        .mechanismOfAction(a.getPharmacogenomic().getMechanismOfAction())
                        .pharmacodynamic(a.getPharmacogenomic().getPharmacodynamic()).build())
                .productAllergyDetails(a.getProductAllergyDetail() == null ? null : ProductAllergyDetail.builder()
                        .detail(a.getProductAllergyDetail().getDetail())
                        .summary(a.getProductAllergyDetail().getSummary()).build())
                .contraindication(a.getContraindication() == null ? null : Contraindication.builder()
                        .value(a.getContraindication().getValue())
                        .relationship(a.getContraindication().getRelationship()).build())
                .isActive(Common.IS_ACTIVE).build();

        return approvalProduct;
    }

    public static ApprovalProduct mapToApprovalProduct(ApprovalProductDetailDTO a) {
        ApprovalProduct approvalProduct = new ApprovalProduct();

        approvalProduct = ApprovalProduct.builder()
                .id(a.getId())
                .name(a.getName())
                .route(a.getRoute())
                .prescriptionName(a.getPrescriptionName())
                .createdOn(LocalDateTime.now())
                .category(Category.builder().id(a.getCategoryId()).build())
                .labeller(Labeller.builder().name(a.getLabeller()).build())
                .manufactor((a.getManufactor() == null) ? null : Manufactor.builder().name(a.getManufactor().getName())
                        .source(a.getManufactor().getSource())
                        .score(a.getManufactor().getScore())
                        .company(a.getManufactor().getCompany())
                        .country(Country.builder().id(a.getManufactor().getCountryId()).build()).build())
                .pharmacogenomic(a.getPharmacogenomic() == null ? null : Pharmacogenomic.builder()
                        .asorption(a.getPharmacogenomic().getAsorption())
                        .toxicity(a.getPharmacogenomic().getToxicity())
                        .indication(a.getPharmacogenomic().getIndication())
                                .mechanismOfAction(a.getPharmacogenomic().getMechanismOfAction())
                                .pharmacodynamic(a.getPharmacogenomic().getPharmacodynamic()).build())
                .productAllergyDetails(a.getProductAllergyDetail() == null ? null : ProductAllergyDetail.builder()
                        .detail(a.getProductAllergyDetail().getDetail())
                                .summary(a.getProductAllergyDetail().getSummary()).build())
                .contraindication(a.getContraindication() == null ? null : Contraindication.builder()
                        .value(a.getContraindication().getValue())
                        .relationship(a.getContraindication().getRelationship()).build())
                .isActive(Common.IS_ACTIVE).build();

        return approvalProduct;
//        approvalProduct.setId(a.getId());
//        approvalProduct.setName(a.getName());
//        approvalProduct.setRoute(a.getRoute());
//        approvalProduct.setPrescriptionName(a.getPrescriptionName());
//        approvalProduct.setCreatedOn(LocalDateTime.now());
//        approvalProduct.setCategory(new Category(a.getCategoryId()));
//        approvalProduct.setLabeller(new Labeller(null, a.getLabeller(), true));
//
//        if (a.getManufactor() != null)
//            approvalProduct.setManufactor( new Manufactor(null, a.getManufactor().getName(),
//                                            a.getManufactor().getCompany(), a.getManufactor().getScore(),
//                                            a.getManufactor().getSource(), new Country(a.getManufactor().getCountryId())) );
//
//        if (a.getPharmacogenomic() != null)
//            approvalProduct.setPharmacogenomic(new Pharmacogenomic(null, a.getPharmacogenomic().getIndication(),
//                    a.getPharmacogenomic().getPharmacodynamic(), a.getPharmacogenomic().getMechanismOfAction(),
//                    a.getPharmacogenomic().getAsorption(), a.getPharmacogenomic().getToxicity()));
//
//        if (a.getContraindication() != null)
//            approvalProduct.setContraindication(new Contraindication(null, a.getContraindication().getRelationship(),
//                                        a.getContraindication().getValue()));
//
//        if (a.getProductAllergyDetail() != null)
//            approvalProduct.setProductAllergyDetails(new ProductAllergyDetail(null, a.getProductAllergyDetail().getDetail(),
//                a.getProductAllergyDetail().getSummary()));
//
//        approvalProduct.setIsActive(Common.IS_ACTIVE);
    }

    public static ProductDetailResponseDTO mapToProductDetaiResponseDTO(ApprovalProduct a,
                                                                        List<Ingredient> ingredients,
                                                                        List<Authority> authorities) {

        return ProductDetailResponseDTO.builder()
                .id(a.getId())
                .route(a.getRoute())
                .name(a.getName())
                .prescriptionName(a.getPrescriptionName())
                .labeller((a.getLabeller() == null) ? null : a.getLabeller().getName())
                .category((a.getCategory() == null) ? null : CategoryResponseDTO.builder()
                        .title(a.getCategory().getTitle())
                        .slug(a.getCategory().getSlug())
                        .id(a.getCategory().getId())
                        .build())
                .manufactor((a.getManufactor() == null) ? null : ManufactorDTO.builder()
                        .name(a.getManufactor().getName())
                        .score(a.getManufactor().getScore())
                        .company(a.getManufactor().getCompany())
                        .source(a.getManufactor().getSource())
                        .countryId((a.getManufactor().getCountry() == null) ? null : a.getManufactor().getCountry().getId())
                        .countryName((a.getManufactor().getCountry() == null) ? null : a.getManufactor().getCountry().getName())
                        .build())
                .pharmacogenomic((a.getPharmacogenomic() == null) ? null : PharmacogenomicDTO.builder()
                        .asorption(a.getPharmacogenomic().getAsorption())
                        .indication(a.getPharmacogenomic().getIndication())
                        .toxicity(a.getPharmacogenomic().getToxicity())
                        .pharmacodynamic(a.getPharmacogenomic().getPharmacodynamic())
                        .mechanismOfAction(a.getPharmacogenomic().getMechanismOfAction())
                        .build())
                .productAllergyDetail((a.getProductAllergyDetails() == null) ? null : ProductAllergyDetailDTO.builder()
                        .detail(a.getProductAllergyDetails().getDetail())
                        .summary(a.getProductAllergyDetails().getSummary())
                        .build())
                .contraindication((a.getContraindication() == null) ? null : ContraindicationDTO.builder()
                        .value(a.getContraindication().getValue())
                        .relationship(a.getContraindication().getRelationship())
                        .build())
                .drugIngredients((ingredients == null) ? null : ingredients.stream()
                                        .map(IngredientMapper::mapToDrugIngredientsResponseDTO).toList())
                .authorities((authorities == null) ? null : authorities.stream()
                                        .map(AuthorityMapper::mapToAuthorityDTO).toList())
                .build();
    }

    public static ProductDetailResponseDTO mapToProductDetaiResponseDTO(Product a,
                                                                        List<Ingredient> ingredients,
                                                                        List<Authority> authorities) {

        return ProductDetailResponseDTO.builder()
                .id(a.getId())
                .route(a.getRoute())
                .name(a.getName())
                .prescriptionName(a.getPrescriptionName())
                .labeller((a.getLabeller() == null) ? null : a.getLabeller().getName())
                .category((a.getCategory() == null) ? null : CategoryResponseDTO.builder()
                        .title(a.getCategory().getTitle())
                        .slug(a.getCategory().getSlug())
                        .id(a.getCategory().getId())
                        .build())
                .manufactor((a.getManufactor() == null) ? null : ManufactorDTO.builder()
                        .name(a.getManufactor().getName())
                        .score(a.getManufactor().getScore())
                        .company(a.getManufactor().getCompany())
                        .source(a.getManufactor().getSource())
                        .countryId((a.getManufactor().getCountry() == null) ? null : a.getManufactor().getCountry().getId())
                        .countryName((a.getManufactor().getCountry() == null) ? null : a.getManufactor().getCountry().getName())
                        .build())
                .pharmacogenomic((a.getPharmacogenomic() == null) ? null : PharmacogenomicDTO.builder()
                        .asorption(a.getPharmacogenomic().getAsorption())
                        .indication(a.getPharmacogenomic().getIndication())
                        .toxicity(a.getPharmacogenomic().getToxicity())
                        .pharmacodynamic(a.getPharmacogenomic().getPharmacodynamic())
                        .mechanismOfAction(a.getPharmacogenomic().getMechanismOfAction())
                        .build())
                .productAllergyDetail((a.getProductAllergyDetails() == null) ? null : ProductAllergyDetailDTO.builder()
                        .detail(a.getProductAllergyDetails().getDetail())
                        .summary(a.getProductAllergyDetails().getSummary())
                        .build())
                .contraindication((a.getContraindication() == null) ? null : ContraindicationDTO.builder()
                        .value(a.getContraindication().getValue())
                        .relationship(a.getContraindication().getRelationship())
                        .build())
                .drugIngredients((ingredients == null) ? null : ingredients.stream()
                        .map(IngredientMapper::mapToDrugIngredientsResponseDTO).toList())
                .authorities((authorities == null) ? null : authorities.stream()
                        .map(AuthorityMapper::mapToAuthorityDTO).toList())
                .build();
    }
}

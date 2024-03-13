package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.*;
import com.example.drugapprovalsystem.model.DTO.*;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.*;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ProductResponseDTO;

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
                .image(a.getImage())
                .productAdministration(
                        a.getProductAdministration() == null ? null :
                                ProductAdministrationDTO.builder()
                                        .id(a.getProductAdministration().getId())
                                        .name(a.getProductAdministration().getName())
                                        .build()

                )
                .build();
    }

    public static ProductResponseDTO mapToProductResponseDTO(Product a) {
        return ProductResponseDTO.builder().id(a.getId())
                .labeller((a.getLabeller() == null) ? null : a.getLabeller().getName())
                .name(a.getName())
                .route(a.getRoute())
                .prescriptionName(a.getPrescriptionName())
                .createdOn(a.getCreatedOn())
                .company((a.getManufactor() == null) ? null : a.getManufactor().getCompany())
                .category((a.getCategory() == null) ? null : a.getCategory().getTitle())
                .image(a.getImage())
                .isApprovedByANSM(a.isApprovedByANSM())
                .isApprovedByFDA(a.isApprovedByFDA())
                .image(a.getImage())
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
                .image(a.getImageURL())
                .isApprovedByANSM(a.isApprovedByANSM())
                .isApprovedByFDA(a.isApprovedByFDA())
                .isActive(Common.IS_ACTIVE).build();

        return product;
    }

    public static ApprovalProduct mapToApprovalProduct(ApprovalProductRequestDTO a) {
        ApprovalProduct approvalProduct;

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
                        .productAdministration(
                                ProductAdministration.builder().id(a.getAdministrationId()).build()
                        )
                .isActive(Common.IS_ACTIVE).build();

        return approvalProduct;
    }

    public static ApprovalProductDetailResponseDTO mapToApprovalProductDetaiResponseDTO(ApprovalProduct a,
                                                                                        List<Ingredient> ingredients,
                                                                                        List<Authority> authorities) {

        return ApprovalProductDetailResponseDTO.builder()
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
                .image(a.getImage())
                .productAdministrationDTO(
                        a.getProductAdministration() == null ? null :
                                ProductAdministrationDTO.builder()
                                        .id(a.getProductAdministration().getId())
                                        .name(a.getProductAdministration().getName())
                                        .build()
                )
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
                .image(a.getImage())
                .isApprovedByANSM(a.isApprovedByANSM())
                .isApprovedByFDA(a.isApprovedByFDA())
                .build();
    }
}

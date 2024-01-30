package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.*;
import com.example.drugapprovalsystem.model.DTO.*;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.*;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductDetailResponseDTO;
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

    public static ApprovalProduct mapToApprovalProduct(ApprovalProductDetailDTO a) {
        ApprovalProduct approvalProduct = new ApprovalProduct();

        approvalProduct.setId(a.getId());
        approvalProduct.setName(a.getName());
        approvalProduct.setRoute(a.getRoute());
        approvalProduct.setPrescriptionName(a.getPrescriptionName());
        approvalProduct.setCreatedOn(LocalDateTime.now());
        approvalProduct.setCategory(new Category(a.getCategoryId()));
        approvalProduct.setLabeller(new Labeller(null, a.getLabeller(), true));

        if (a.getManufactor() != null)
            approvalProduct.setManufactor( new Manufactor(null, a.getManufactor().getName(),
                                            a.getManufactor().getCompany(), a.getManufactor().getScore(),
                                            a.getManufactor().getSource(), new Country(a.getManufactor().getCountryId())) );

        if (a.getPharmacogenomic() != null)
            approvalProduct.setPharmacogenomic(new Pharmacogenomic(null, a.getPharmacogenomic().getIndication(),
                    a.getPharmacogenomic().getPharmacodynamic(), a.getPharmacogenomic().getMechanismOfAction(),
                    a.getPharmacogenomic().getAsorption(), a.getPharmacogenomic().getToxicity()));

        if (a.getContraindication() != null)
            approvalProduct.setContraindication(new Contraindication(null, a.getContraindication().getRelationship(),
                                        a.getContraindication().getValue()));

        if (a.getProductAllergyDetail() != null)
            approvalProduct.setProductAllergyDetails(new ProductAllergyDetail(null, a.getProductAllergyDetail().getDetail(),
                a.getProductAllergyDetail().getSummary()));

        approvalProduct.setIsActive(Common.IS_ACTIVE);

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

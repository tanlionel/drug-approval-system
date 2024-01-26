package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.*;
import com.example.drugapprovalsystem.model.DTO.product_dto.*;

import java.time.Instant;
import java.time.LocalDateTime;

public class ProductMapper {
    public final static ApprovalProductResponseDTO mapToApprovalProductResponseDTO(ApprovalProduct a) {
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

    public final static ApprovalProduct mapToApprovalProduct(ApprovalProductRequestDTO a) {
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

        return approvalProduct;
    }
}

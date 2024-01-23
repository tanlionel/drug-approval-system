package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.entity.ApprovalProduct;
import com.example.drugapprovalsystem.model.DTO.ApprovalProductResponseDTO;

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
}

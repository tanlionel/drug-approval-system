package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.entity.Drug;
import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.model.DTO.DrugResponseDTO;

public class DrugMapper {

    public static final DrugResponseDTO mapToDrugResponseDTO(Drug drug){
        return DrugResponseDTO.builder()
                .id(drug.getId())
                .type(drug.getType())
                .drugbankId(drug.getDrugbankId())
                .name(drug.getName())
                .state(drug.getState())
                .description(drug.getDescription())
                .simpleDescription(drug.getSimpleDescription())
                .approvalStatus(drug.getApprovalStatus())
                .clinicalDescription(drug.getClinicalDescription())
                .build();

    }
}

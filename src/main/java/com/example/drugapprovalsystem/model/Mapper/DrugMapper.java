package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.entity.Drug;
import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.model.DTO.DrugRequestDTO;
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
                .isActive(drug.getIsActive())
                .build();

    }

    public static final Drug mapToDrug(DrugRequestDTO drugRequestDTO) {
        Drug drug = new Drug();
        drug.setType(drugRequestDTO.getType());
        drug.setName(drugRequestDTO.getName());
        drug.setState(drugRequestDTO.getState());
        drug.setDescription(drugRequestDTO.getDescription());
        drug.setSimpleDescription(drugRequestDTO.getSimpleDescription());
        drug.setClinicalDescription(drugRequestDTO.getClinicalDescription());
        drug.setApprovalStatus(drugRequestDTO.getApprovalStatus());

        return drug;
    }

}

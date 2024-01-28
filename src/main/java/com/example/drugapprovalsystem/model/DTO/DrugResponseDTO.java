package com.example.drugapprovalsystem.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DrugResponseDTO {

    private Integer id;
    private String type;
    private Integer drugbankId;
    private String name;
    private String state;
    private String description;
    private String simpleDescription;
    private String clinicalDescription;
    private Integer approvalStatus;

}

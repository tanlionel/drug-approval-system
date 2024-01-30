package com.example.drugapprovalsystem.model.DTO;

import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DrugRequestDTO {
    private Integer id;
    private String type;
    private String name;
    private String state;
    private String description;
    private String simpleDescription;
    private String clinicalDescription;
    private Integer approvalStatus;
}

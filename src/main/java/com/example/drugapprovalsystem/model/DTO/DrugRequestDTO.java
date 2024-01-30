package com.example.drugapprovalsystem.model.DTO;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

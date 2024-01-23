package com.example.drugapprovalsystem.model.DTO;

import lombok.Data;

@Data
public class DrugRequestDTO {

    private String type;
    private String name;
    private String state;
    private String description;
    private String simpleDescription;
    private String clinicalDescription;
    private Integer approvalStatus;

}

package com.example.drugapprovalsystem.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ApprovalProductResponseDTO {
    private Integer id;
    private String labeller;
    private String name;
    private String route;
    private String prescriptionName;
    //@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss yyyy-MM-dd")
    private Instant createdOn;
    private String company;
    private String category;
}

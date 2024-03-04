package com.example.drugapprovalsystem.model.DTO.product_response_dto;

import com.example.drugapprovalsystem.model.DTO.ProductAdministrationDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {
    private Integer id;
    private String labeller;
    private String name;
    private String route;
    private String prescriptionName;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss yyyy-MM-dd")
    private LocalDateTime createdOn;

    private String company;
    private String category;
    private String image;
    private boolean isApprovedByFDA;
    private boolean isApprovedByANSM;
}

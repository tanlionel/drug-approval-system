package com.example.drugapprovalsystem.model.DTO.product_request_dto;

import com.example.drugapprovalsystem.model.DTO.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDTO {
    private String labeller;
    private String name;
    private String route;
    private String prescriptionName;
    private List<DrugIngredientsDTO> drugIngredients;
    private Integer categoryId;
    private ManuFactorRequestDTO manufactor;
    private List<AuthorityRequestDTO> authorities;
    private PharmacogenomicDTO pharmacogenomic;
    private ProductAllergyDetailDTO productAllergyDetail;
    private ContraindicationDTO contraindication;
}

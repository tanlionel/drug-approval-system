package com.example.drugapprovalsystem.model.DTO.product_response_dto;

import com.example.drugapprovalsystem.entity.Category;
import com.example.drugapprovalsystem.entity.Manufactor;
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
public class ApprovalProductDetailResponseDTO {
    private Integer id;
    private String labeller;
    private String name;
    private String route;
    private String prescriptionName;
    private List<DrugIngredientsResponseDTO> drugIngredients;
    private CategoryResponseDTO category;
    private ManufactorDTO manufactor;
    private List<AuthorityDTO> authorities;
    private PharmacogenomicDTO pharmacogenomic;
    private ProductAllergyDetailDTO productAllergyDetail;
    private ContraindicationDTO contraindication;
    @JsonIgnore
    public void setId(Integer id) {
        this.id = id;
    }
}

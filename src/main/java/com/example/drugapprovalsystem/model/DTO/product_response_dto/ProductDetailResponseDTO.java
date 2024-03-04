package com.example.drugapprovalsystem.model.DTO.product_response_dto;

import com.example.drugapprovalsystem.model.DTO.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailResponseDTO {
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
    private String image;
}

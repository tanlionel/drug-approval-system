package com.example.drugapprovalsystem.model.DTO.product_dto;

import com.example.drugapprovalsystem.entity.Contraindication;
import com.example.drugapprovalsystem.entity.Manufactor;
import com.example.drugapprovalsystem.entity.Pharmacogenomic;
import com.example.drugapprovalsystem.entity.ProductAllergyDetail;
import lombok.*;

import java.beans.ConstructorProperties;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalProductRequestDTO {
    private Integer id;
    private String labeller;
    private String name;
    private String route;
    private String prescriptionName;
    private List<DrugIngredientsRequestDTO> drugIngredients;
    private Integer categoryId;
    private ManufactorRequestDTO manufactor;
    private List<AuthorityRequestDTO> authorities;
    private PharmacogenomicRequestDTO pharmacogenomic;
    private ProductAllergyDetailRequestDTO productAllergyDetail;
    private ContraindicationRequestDTO contraindication;
}

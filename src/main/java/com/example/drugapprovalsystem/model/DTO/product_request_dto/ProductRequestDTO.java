package com.example.drugapprovalsystem.model.DTO.product_request_dto;

import com.example.drugapprovalsystem.model.DTO.ContraindicationDTO;
import com.example.drugapprovalsystem.model.DTO.DrugIngredientsDTO;
import com.example.drugapprovalsystem.model.DTO.PharmacogenomicDTO;
import com.example.drugapprovalsystem.model.DTO.ProductAllergyDetailDTO;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private String imageURL;
    private boolean isApprovedByFDA;
    private boolean isApprovedByANSM;
}

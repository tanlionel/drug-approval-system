package com.example.drugapprovalsystem.model.DTO.product_response_dto;

import com.example.drugapprovalsystem.entity.Country;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManufactorResponseDTO {
    private Integer id;

    private String name;

    private String company;

    private String score;

    private String source;

    private Integer countryId;

    private String countryName;
}

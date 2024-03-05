package com.example.drugapprovalsystem.model.DTO;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductAdministrationDTO {
    private Integer id;
    private String name;
}

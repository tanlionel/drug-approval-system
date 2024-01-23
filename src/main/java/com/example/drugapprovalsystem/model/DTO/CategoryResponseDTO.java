package com.example.drugapprovalsystem.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponseDTO {
    private int id;
    private String title;
    private String slug;
}

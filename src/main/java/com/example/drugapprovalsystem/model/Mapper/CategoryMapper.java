package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.entity.Category;
import com.example.drugapprovalsystem.model.DTO.CategoryResponseDTO;

public class CategoryMapper {
    public final static CategoryResponseDTO mapToCategoryResponseDTO(Category c) {
        return CategoryResponseDTO.builder().id(c.getId())
                .title(c.getTitle())
                .slug(c.getSlug())
                .build();
    }
}

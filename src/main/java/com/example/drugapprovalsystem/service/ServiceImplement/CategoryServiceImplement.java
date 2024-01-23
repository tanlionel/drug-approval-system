package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.model.DTO.CategoryResponseDTO;
import com.example.drugapprovalsystem.model.Mapper.CategoryMapper;
import com.example.drugapprovalsystem.repository.CategoryRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.CategoryService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplement implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDTO> getAllCategoryByTitle(String title) {
        return categoryRepository.findByTitleContainingAndIsActive(Sort.by("title").ascending(), title, Common.IS_ACTIVE)
                .stream().map(CategoryMapper::mapToCategoryResponseDTO)
                .toList();
    }
}

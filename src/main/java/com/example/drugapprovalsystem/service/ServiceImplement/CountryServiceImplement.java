package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.Country;
import com.example.drugapprovalsystem.model.DTO.CountryResponseDTO;
import com.example.drugapprovalsystem.repository.CountryRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImplement implements CountryService {
    @Autowired
    CountryRepository countryRepository;
    @Override
    public List<CountryResponseDTO> getAllCountryByName(String name) {
        return countryRepository
                .findByNameContainingAndIsActive(Sort.by("name").ascending(), name, Common.IS_ACTIVE)
                .stream()
                .map(c -> CountryResponseDTO.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .build())
                .toList();
    }
}

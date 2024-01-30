package com.example.drugapprovalsystem.service.ServiceInterface;

import com.example.drugapprovalsystem.entity.Country;
import com.example.drugapprovalsystem.model.DTO.CountryResponseDTO;

import java.util.List;

public interface CountryService {

    public List<CountryResponseDTO> getAllCountryByName(String name);

}

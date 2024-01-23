package com.example.drugapprovalsystem.service.ServiceInterface;

import com.example.drugapprovalsystem.entity.Country;

import java.util.List;

public interface CountryService {

    public List<Country> getAllCountryByName(String name);

}

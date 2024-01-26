package com.example.drugapprovalsystem.service.ServiceInterface;

import com.example.drugapprovalsystem.model.DTO.DrugRequestDTO;
import com.example.drugapprovalsystem.model.DTO.DrugResponseDTO;
import org.springframework.data.domain.Page;

public interface DrugService {

    Page<DrugResponseDTO> getDrugPageable(Integer pageNo,Integer pageSize,String sortField,String sortOrder, String search);

    public void createDrug(DrugRequestDTO drugRequestDTO);
    public void updateDrug(DrugRequestDTO drugRequestDTO) throws Exception;
}

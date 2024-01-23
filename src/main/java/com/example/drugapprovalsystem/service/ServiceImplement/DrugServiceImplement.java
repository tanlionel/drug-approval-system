package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.entity.Drug;
import com.example.drugapprovalsystem.model.DTO.DrugRequestDTO;
import com.example.drugapprovalsystem.model.DTO.DrugResponseDTO;
import com.example.drugapprovalsystem.model.Mapper.DrugMapper;
import com.example.drugapprovalsystem.repository.DrugRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.DrugService;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrugServiceImplement implements DrugService {

    private static final String SORT_ASC = "asc";

    @Autowired
    DrugRepository drugRepository;

    public Page<DrugResponseDTO> getDrugPageable(Integer pageNo, Integer pageSize, String sortField, String sortOrder, String search) {
        Pageable pageable;
        if (sortOrder.equals(SORT_ASC)) {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).ascending());
        } else {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortField).descending());
        }
        Page<Drug> drugPageable;
        drugPageable = drugRepository.findAll(pageable);
        drugPageable = drugRepository.findAllByNameContaining(pageable, search);
        return drugPageable.map(DrugMapper::mapToDrugResponseDTO);
    }

    @Override
    public void createDrug(DrugRequestDTO drugRequestDTO) {
        Drug drug = DrugMapper.mapToDrug(drugRequestDTO);

        Drug result = drugRepository.save(drug);

    }

}

package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.entity.Drug;
import com.example.drugapprovalsystem.exception.DrugDoesNotExistException;
import com.example.drugapprovalsystem.exception.ProductDoesNotExistException;
import com.example.drugapprovalsystem.model.DTO.DrugRequestDTO;
import com.example.drugapprovalsystem.model.DTO.DrugResponseDTO;
import com.example.drugapprovalsystem.model.Mapper.DrugMapper;
import com.example.drugapprovalsystem.repository.DrugRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.DrugService;
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

    @Override
    public void updateDrug(DrugRequestDTO drugRequestDTO) throws Exception {
        if(drugRequestDTO.getId() == null)
            throw new DrugDoesNotExistException();

        Drug drug = DrugMapper.mapToDrug(drugRequestDTO);

        Drug drugInDB = drugRepository.findById(drugRequestDTO.getId()).get();

        if (drugInDB == null)
            throw new ProductDoesNotExistException();

        //Update Drug
        if(drugRequestDTO.getType() != null) {
            drugInDB.setType(drugRequestDTO.getType());
        }

        if(drugRequestDTO.getName() != null) {
            drugInDB.setName(drugRequestDTO.getName());
        }

        if(drugRequestDTO.getState() != null) {
            drugInDB.setState(drugRequestDTO.getState());
        }

        if(drugRequestDTO.getDescription() != null) {
            drugInDB.setDescription(drugRequestDTO.getDescription());
        }

        if(drugRequestDTO.getSimpleDescription() != null) {
            drugInDB.setSimpleDescription(drugRequestDTO.getSimpleDescription());
        }

        if(drugRequestDTO.getClinicalDescription() != null) {
            drugInDB.setClinicalDescription(drugRequestDTO.getClinicalDescription());
        }

        if(drugRequestDTO.getApprovalStatus() != null) {
            drugInDB.setApprovalStatus(drugRequestDTO.getApprovalStatus());
        }

            //Set created by
            //Save
            Drug result = drugRepository.save(drug);

    }

}

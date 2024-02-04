package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.entity.Drug;
import com.example.drugapprovalsystem.exception.DrugDoesNotExistException;
import com.example.drugapprovalsystem.exception.InvalidActionException;
import com.example.drugapprovalsystem.model.DTO.DrugRequestDTO;
import com.example.drugapprovalsystem.model.DTO.DrugResponseDTO;
import com.example.drugapprovalsystem.model.DTO.UpdateDrugRequestDTO;
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

import java.util.Optional;

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
        drug.setIsActive(true);
        Drug result = drugRepository.save(drug);

    }

    @Override
    public Drug updateDrug(UpdateDrugRequestDTO updateDrugRequestDTO, Integer drugId) throws Exception {
        Optional<Drug> drugInDB = drugRepository.findById(drugId);
        if (drugInDB == null) throw new DrugDoesNotExistException();

        //Update Drug
        if(updateDrugRequestDTO.getType() != null) drugInDB.get().setType(updateDrugRequestDTO.getType());
        if(updateDrugRequestDTO.getName() != null) drugInDB.get().setName(updateDrugRequestDTO.getName());
        if(updateDrugRequestDTO.getState() != null) drugInDB.get().setState(updateDrugRequestDTO.getState());
        if(updateDrugRequestDTO.getDescription() != null) drugInDB.get().setDescription(updateDrugRequestDTO.getDescription());
        if(updateDrugRequestDTO.getSimpleDescription() != null) drugInDB.get().setSimpleDescription(updateDrugRequestDTO.getSimpleDescription());
        if(updateDrugRequestDTO.getClinicalDescription() != null) drugInDB.get().setClinicalDescription(updateDrugRequestDTO.getClinicalDescription());
        if(updateDrugRequestDTO.getApprovalStatus() != null) drugInDB.get().setApprovalStatus(updateDrugRequestDTO.getApprovalStatus());
        //Update IsActive
        drugInDB.get().setIsActive(true);
        //Save
        return drugRepository.save(drugInDB.get());

    }

    @Override
    public void deleteDrug(Integer drugId) throws Exception {
        Optional<Drug> optional = drugRepository.findById(drugId);
        if(optional.isEmpty()) throw new DrugDoesNotExistException();
        Drug drug = optional.get();
        if(!drug.getIsActive()) throw new InvalidActionException();

        drug.setIsActive(!drug.getIsActive());
        drugRepository.save(drug);
    }

}

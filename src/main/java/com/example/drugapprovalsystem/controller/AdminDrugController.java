package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.Drug;
import com.example.drugapprovalsystem.model.DTO.DrugRequestDTO;
import com.example.drugapprovalsystem.model.DTO.UpdateDrugRequestDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.DrugService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@CrossOrigin
public class AdminDrugController {
    @Autowired
    DrugService drugService;
    @GetMapping("/drug-management/drugs")
    public ResponseEntity<?> getDrugPageable(@RequestParam(defaultValue = "0") int pageNo,
                                             @RequestParam(defaultValue = "10") int pageSize,
                                             @RequestParam(defaultValue = "id") String sortField,
                                             @RequestParam(defaultValue = Common.SORT_ASC) String sortOrder,
                                             @RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(drugService.getDrugPageable(pageNo,pageSize,sortField,sortOrder, search));
    }

    @PostMapping("/drug-management/drug/create")
    public void createDrug(@RequestBody DrugRequestDTO drugRequestDTO){
        drugService.createDrug(drugRequestDTO);
    }

    @PutMapping("/drug-management/drug/update/")
    public ResponseEntity<?> updateByDrugId(@RequestParam Integer drugId,
                                            @RequestBody UpdateDrugRequestDTO updateDrugRequestDTO) throws Exception{
        Drug drug = drugService.updateDrug(updateDrugRequestDTO, drugId);
        return ResponseEntity.ok(UpdateDrugRequestDTO.builder()
                .type(drug.getType())
                .name(drug.getName())
                .state(drug.getState())
                .description(drug.getDescription())
                .simpleDescription(drug.getSimpleDescription())
                .clinicalDescription(drug.getClinicalDescription())
                .approvalStatus(drug.getApprovalStatus())
                .build());
    }

    @DeleteMapping("/drug-management/drug/delete")
    public void deleteDrugById(@RequestParam("id") Integer drugId) throws Exception {
        drugService.deleteDrug(drugId);

    }

}

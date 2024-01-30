package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.Drug;
import com.example.drugapprovalsystem.model.DTO.DrugRequestDTO;
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

    @PostMapping("/drug-management/drugs/create")
    public void createDrug(@RequestBody DrugRequestDTO drugRequestDTO){
        drugService.createDrug(drugRequestDTO);
    }

    @PutMapping("/drug-management/drugs/update/{drugId}")
    public ResponseEntity<?> updateByDrugId(@RequestParam("id") Integer id,
                               @RequestBody DrugRequestDTO dto) throws Exception{
        Drug drug = drugService.updateDrug(dto);
        return ResponseEntity.ok(DrugRequestDTO.builder()
                .type(drug.getType())
                .name(drug.getName())
                .state(drug.getState())
                .description(drug.getDescription())
                .simpleDescription(drug.getSimpleDescription())
                .clinicalDescription(drug.getClinicalDescription())
                .approvalStatus(drug.getApprovalStatus())
                .build());
    }

}

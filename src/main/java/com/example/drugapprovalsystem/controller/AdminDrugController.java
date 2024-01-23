package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
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
    @GetMapping("/drug")
    public ResponseEntity<?> getDrugPageable(@RequestParam(defaultValue = "0") int pageNo,
                                             @RequestParam(defaultValue = "10") int pageSize,
                                             @RequestParam(defaultValue = "id") String sortField,
                                             @RequestParam(defaultValue = Common.SORT_ASC) String sortOrder,
                                             @RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(drugService.getDrugPageable(pageNo,pageSize,sortField,sortOrder, search));
    }

    @PostMapping("/drug")
    public void createDrug(@RequestBody DrugRequestDTO drugRequestDTO){
        drugService.createDrug(drugRequestDTO);
    }

}

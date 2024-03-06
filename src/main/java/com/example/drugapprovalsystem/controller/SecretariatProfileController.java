package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepOneDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.ProfileProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secretariat")
@AllArgsConstructor
@CrossOrigin

public class SecretariatProfileController {

    @Autowired
    ProfileProductService profileProductService;


    @PostMapping("/profileproduct/create")
    public ResponseEntity<?> createProfile(@RequestBody ProfileRequestStepOneDTO profileRequestStepOneDTO) throws Exception {
        return ResponseEntity
                .ok(profileProductService.createProfile(profileRequestStepOneDTO));
    }

}

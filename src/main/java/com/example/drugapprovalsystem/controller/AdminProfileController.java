package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.Profile;
import com.example.drugapprovalsystem.model.DTO.DrugRequestDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepOneDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepTwoDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepTwoUpdateDTO;
import com.example.drugapprovalsystem.repository.ProfileProductRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.ProfileProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@CrossOrigin

public class AdminProfileController {
    @Autowired
    ProfileProductService profileProductService;

    @GetMapping("/profile-product")
    public ResponseEntity<?> getProfilesPageable(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                 @RequestParam(value = "search", defaultValue = "") String search) {

        return ResponseEntity
                .ok(profileProductService.getAllProfilesPageable(pageNo, pageSize, search));
    }

    @GetMapping("/profile-product-details")
    public ResponseEntity<?> getProfileDetails(@RequestParam("id") int id) throws Exception {

        return ResponseEntity
                .ok(profileProductService.getProfileDetails(id));
    }

    @PostMapping("/profile-product/create-step-one")
    public ResponseEntity<?> createProfile(@RequestBody ProfileRequestStepOneDTO profileRequestStepOneDTO) throws Exception {
       return ResponseEntity
               .ok(profileProductService.createProfile(profileRequestStepOneDTO));
    }

    @PutMapping("/profile-product/update-step-one")
    public ResponseEntity<?> updateProfileStepOne(@RequestParam int profileId,
                                                  @RequestBody ProfileRequestStepOneDTO profileRequestStepOneDTO) throws Exception{

        return ResponseEntity.ok(
                profileProductService.updateProfileDetail(profileId, profileRequestStepOneDTO)
        );

    }

    @PostMapping("/profile-product/create-step-two")
    public void createProfileDetail(@RequestBody ProfileRequestStepTwoDTO profileRequestStepTwoDTO) throws Exception {
        profileProductService.createProfileDetail(profileRequestStepTwoDTO);
    }

    @PutMapping("/profile-product/update-step-two")
    public void updateProfileStepTwo(@RequestBody ProfileRequestStepTwoUpdateDTO profileRequestStepTwoUpdateDTO) throws Exception{

        profileProductService.updateProfileDetail(profileRequestStepTwoUpdateDTO);

    }
}

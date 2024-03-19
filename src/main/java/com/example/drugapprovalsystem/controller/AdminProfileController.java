package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepOneDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepTwoDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileRequestStepTwoUpdateDTO;
import com.example.drugapprovalsystem.model.DTO.profile_request_dto.ProfileDetailSubmitRequestDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.ProfileProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@CrossOrigin

public class AdminProfileController {
    @Autowired
    ProfileProductService profileProductService;

    @GetMapping("/profile-products")
    public ResponseEntity<?> getProfilesPageable(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                 @RequestParam(value = "search", defaultValue = "") String search) throws Exception {

        return ResponseEntity
                .ok(profileProductService.getAllProfilesPageable(pageNo, pageSize, search));
    }

    @GetMapping("/profile-products-details")
    public ResponseEntity<?> getProfileDetails(@RequestParam("id") int id) throws Exception {

        return ResponseEntity
                .ok(profileProductService.getProfileDetails(id));
    }
    @GetMapping("/profile-products-details-update-step-two")
    public ResponseEntity<?> getProfileDetailsForUpdate(@RequestParam("profileId") int profileId) throws Exception {
        return ResponseEntity.ok(
            profileProductService.getProfileDetailForUpdate(profileId)
        );
    }

    @PostMapping("/profile-products/step-one")
    public ResponseEntity<?> createProfile(@RequestBody ProfileRequestStepOneDTO profileRequestStepOneDTO) throws Exception {
       return ResponseEntity
               .ok(profileProductService.createProfile(profileRequestStepOneDTO));
    }

    @PostMapping("/profile-products/create-or-update-step-two")
    public ResponseEntity<?> createOrUpdate(@RequestBody ProfileRequestStepTwoUpdateDTO dto) throws Exception {

        return ResponseEntity
                .ok(profileProductService.createOrUpdateProfileDetail(dto));

    }
    @PutMapping("/profile-products/step-one")
    public ResponseEntity<?> updateProfileStepOne(@RequestParam int profileId,
                                                  @RequestBody ProfileRequestStepOneDTO profileRequestStepOneDTO) throws Exception{

        return ResponseEntity.ok(
                profileProductService.updateProfileDetail(profileId, profileRequestStepOneDTO)
        );
    }

    @PostMapping("/profile-products/step-two")
    public ResponseEntity<?> createProfileDetail(@RequestBody ProfileRequestStepTwoDTO profileRequestStepTwoDTO) throws Exception {
        return ResponseEntity.ok(
                profileProductService.createProfileDetail(profileRequestStepTwoDTO));
    }

    @PutMapping("/profile-products/step-two")
    public ResponseEntity<?> updateProfileStepTwo(@RequestBody ProfileRequestStepTwoUpdateDTO profileRequestStepTwoUpdateDTO) throws Exception{
        return ResponseEntity.ok(
                profileProductService.updateProfileDetail(profileRequestStepTwoUpdateDTO));

    }

    @PostMapping("profile-products/submission")
    public ResponseEntity<?> submitProfile(@RequestParam int profileId, @RequestBody List<ProfileDetailSubmitRequestDTO> submitRequestDTO) throws Exception {

        return ResponseEntity.ok(
                profileProductService.submitProfile(profileId, submitRequestDTO));

    }
    @PostMapping("/profile-products/process")
    public ResponseEntity<?> processProfile(@RequestParam int profileId) throws Exception {
        return ResponseEntity.ok(
                profileProductService.processingProfile(profileId));
    }
}

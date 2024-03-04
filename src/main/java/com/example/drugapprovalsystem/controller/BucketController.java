package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.entity.ApprovalProduct;
import com.example.drugapprovalsystem.entity.User;
import com.example.drugapprovalsystem.exception.ProductDoesNotExistException;
import com.example.drugapprovalsystem.exception.UserDoesNotExistException;
import com.example.drugapprovalsystem.model.DTO.UserResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductResponseDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.ApprovalProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import com.example.drugapprovalsystem.ulity.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/storage/")
public class BucketController {
    @Autowired
    public UserService userService;
    @Autowired
    public ApprovalProductService approvalProductService;

    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonClient.uploadFile(file);
    }
    @PostMapping("/user")
    public ResponseEntity<?> uploadUserAvatar(@RequestPart(value = "file") MultipartFile file, @RequestParam String email) throws UserDoesNotExistException {
        UserResponseDTO user = userService.uploadAvatar(email,this.amazonClient.uploadFile(file));
        return ResponseEntity.ok(user);
    }
    @PostMapping("/approval-product")
    public ResponseEntity<?> uploadApprovalProductAvatar(@RequestPart(value = "file") MultipartFile file, @RequestParam Integer ApprovalProductID) throws ProductDoesNotExistException {
        ApprovalProductResponseDTO approvalProductResponseDTO = approvalProductService.uploadImage(ApprovalProductID,this.amazonClient.uploadFile(file));
        return ResponseEntity.ok(approvalProductResponseDTO);
    }
}


package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.model.DTO.EmailRequestDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/profile-products")
    public ResponseEntity<?> sendMail(@RequestParam String to){
        return emailService.sendMail(to);
    }

    @PostMapping("/Optional")
    public ResponseEntity<?> sendMailOptional(@RequestParam String to, @RequestBody EmailRequestDTO emailRequestDTO){
        return emailService.sendMail(to,emailRequestDTO.getContent(),emailRequestDTO.getTesText());
    }
}

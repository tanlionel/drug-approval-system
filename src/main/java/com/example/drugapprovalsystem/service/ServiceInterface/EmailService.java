package com.example.drugapprovalsystem.service.ServiceInterface;

import org.springframework.http.ResponseEntity;

public interface EmailService {
    ResponseEntity<?> sendMail(String to);
}

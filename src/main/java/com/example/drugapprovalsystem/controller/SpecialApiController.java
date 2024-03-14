package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SpecialApiController {
    @Autowired
    UserService userService;

    @GetMapping("admin")
    public ResponseEntity<?> getAdminList(@RequestParam(required = false) String email){
        return ResponseEntity.of(Optional.ofNullable(userService.getAdmin(email)));
    }
    @GetMapping("secretary")
    public ResponseEntity<?> getSecretaryList(@RequestParam(required = false) String email){
        return ResponseEntity.of(Optional.ofNullable(userService.getSecretary(email)));
    }
    @GetMapping("email")
    public ResponseEntity<?> getUserByEmail(@RequestParam(required = false) String email){
        return ResponseEntity.of(Optional.ofNullable(userService.getUser(email)));
    }
}

package com.example.drugapprovalsystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
@CrossOrigin
public class TestController {
    @GetMapping("/test")
    public String getTest(){
        return "Hello from test";
    }
    @GetMapping("/secure")
    public String getSecure(){
        return "Hello from secure";
    }
}

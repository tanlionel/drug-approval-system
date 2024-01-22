package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.service.ServiceInterface.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    DrugService drugService;
    @GetMapping("/test/drug")
    public ResponseEntity<?> getDrugPageable(@RequestParam(required = false,defaultValue = "0") Integer pageNo,
                                              @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false,defaultValue = "id") String sortField,
                                              @RequestParam(required = false,defaultValue = "asc") String sortOrder)

    {return ResponseEntity.ok(drugService.getDrugPageable(pageNo,pageSize,sortField,sortOrder));}                                         ;

}

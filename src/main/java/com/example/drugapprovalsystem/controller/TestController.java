package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.ApprovalProduct;
import com.example.drugapprovalsystem.repository.ApprovalProductRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.ApprovalProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
@CrossOrigin
public class TestController {
    @Autowired
    ApprovalProductService approvalProductService;
    @Autowired
    ApprovalProductRepository approvalProductRepository;
    @GetMapping("/test")
    public String getTest(){
        return "Hello from test";
    }
    @GetMapping("/secure")
    public String getSecure(){
        return "Hello from secure";
    }
    @GetMapping("/test/admin/approval-product")
    public ResponseEntity<?> getApprovalProductWithPageable(@RequestParam(defaultValue = "0") int pageNo,
                                                            @RequestParam(defaultValue = "10") int pageSize,
                                                            @RequestParam(defaultValue = "id") String sortField,
                                                            @RequestParam(defaultValue = Common.SORT_ASC) String sortOrder,
                                                            @RequestParam(defaultValue = "") String search) {

        return ResponseEntity.ok(approvalProductService.getPageableApprovalProduct(pageNo, pageSize, sortField, sortOrder, search));
    }
}

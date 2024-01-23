package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.ApprovalProduct;
import com.example.drugapprovalsystem.model.DTO.product_dto.ApprovalProductRequestDTO;
import com.example.drugapprovalsystem.repository.ApprovalProductRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.ApprovalProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.CategoryService;
import com.example.drugapprovalsystem.service.ServiceInterface.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    CategoryService categoryService;
    @Autowired
    CountryService countryService;
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

    @GetMapping("/test/public/category")
    public ResponseEntity<?> getAllCategory(@RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(categoryService.getAllCategoryByTitle(search));
    }

    @GetMapping("/test/public/country")
    public ResponseEntity<?> getAllCountry(@RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(countryService.getAllCountryByName(search));
    }

    @PostMapping("/test/admin/approval-product")
    public void createApprovalProduct(@RequestBody ApprovalProductRequestDTO dto) {
        System.out.println("TestController: RUN createApprovalProduct");

        approvalProductService.createApprovalProduct(dto);

        System.out.println("TestController: RUN Mapping successfully");
    }
}

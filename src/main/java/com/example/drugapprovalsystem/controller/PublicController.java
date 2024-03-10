package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.enums.UserRole;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ApprovalProductDetailDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.ApprovalProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.CategoryService;
import com.example.drugapprovalsystem.service.ServiceInterface.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
@CrossOrigin
public class PublicController {
    @Autowired
    ApprovalProductService approvalProductService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CountryService countryService;

    //get all approval product api
    @GetMapping("/approval-products")
    public ResponseEntity<?> getApprovalProductWithPageable(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                                            @RequestParam(value = "sortOrder", defaultValue = Common.SORT_ASC) String sortOrder,
                                                            @RequestParam(value = "search", defaultValue = "") String search) {

        return ResponseEntity
                .ok(approvalProductService.getPageableApprovalProduct(pageNo, pageSize, sortField, sortOrder, search));
    }

    @GetMapping("/approval-product-detail")
    public ResponseEntity<?> getApprovalProductById(@RequestParam Integer id) throws Exception {
        return ResponseEntity
                .ok(approvalProductService.getApprovalProductDetail(id));
    }

    //get all category api
    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategory(@RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(categoryService.getAllCategoryByTitle(search));
    }

    //get all country
    @GetMapping("/countries")
    public ResponseEntity<?> getAllCountry(@RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(countryService.getAllCountryByName(search));
    }

}

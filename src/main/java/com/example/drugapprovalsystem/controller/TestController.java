package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.model.DTO.DrugRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ApprovalProductDetailDTO;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ProductRequestDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
@CrossOrigin
public class TestController {
    @Autowired
    ProductService productService;
    @GetMapping("/test")
    public String getTest(){
        return "Hello from test";
    }
    @GetMapping("/secure")
    public String getSecure(){
        return "Hello from secure";
    }

    @PostMapping("/test/product/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDTO productRequestDTO) throws Exception {
        return ResponseEntity.ok(productService.createProduct(productRequestDTO));
    }

    @GetMapping("/test/product-detail")
    public ResponseEntity<?> getProductById(@RequestParam Integer id) throws  Exception {
        return ResponseEntity
                .ok(productService.getProductDetail(id));
    }

    @PutMapping("/test/product/update")
    public ResponseEntity<?> updateProductById(@RequestParam("id") Integer id,
                                               @RequestBody ProductRequestDTO productDetailDTO) throws Exception {

        return ResponseEntity.ok(productService.updateProduct(id, productDetailDTO));
    }

}

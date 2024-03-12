package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ApprovalProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ProductRequestDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.ApprovalProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secretariat")
@AllArgsConstructor
@CrossOrigin
public class SecretariatProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDTO productRequestDTO) throws Exception {
        return ResponseEntity.ok(productService.createProduct(productRequestDTO));
    }

    @GetMapping("/products-detail")
    public ResponseEntity<?> getProductById(@RequestParam Integer id) throws  Exception {
        return ResponseEntity
                .ok(productService.getProductDetail(id));
    }

    @PutMapping("/products")
    public ResponseEntity<?> updateProductById(@RequestParam("id") Integer id,
                                                       @RequestBody ProductRequestDTO productDetailDTO) throws Exception {

        return ResponseEntity.ok(productService.updateProduct(id, productDetailDTO));
    }
}

package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ApprovalProductDetailDTO;
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
    ApprovalProductService approvalProductService;
    @Autowired
    ProductService productService;
    @GetMapping("/approval-product")
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
        return ResponseEntity.ok(approvalProductService.getApprovalProductDetail(id));
    }

    @PostMapping("/product/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDTO productRequestDTO) throws Exception {
        return ResponseEntity.ok(productService.createProduct(productRequestDTO));
    }

    @GetMapping("/product-detail")
    public ResponseEntity<?> getProductById(@RequestParam Integer id) throws  Exception {
        return ResponseEntity
                .ok(productService.getProductDetail(id));
    }

    @PutMapping("/product/update")
    public ResponseEntity<?> updateProductById(@RequestParam("id") Integer id,
                                                       @RequestBody ProductRequestDTO productDetailDTO) throws Exception {

        return ResponseEntity.ok(productService.updateProduct(id, productDetailDTO));
    }
}

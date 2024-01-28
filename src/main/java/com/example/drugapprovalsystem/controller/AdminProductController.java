package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ApprovalProductDetailDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.ApprovalProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@CrossOrigin
public class AdminProductController {

    @Autowired
    ApprovalProductService approvalProductService;

    @GetMapping("/approval-product")
    public ResponseEntity<?> getApprovalProductWithPageable(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                                            @RequestParam(value = "sortOrder", defaultValue = Common.SORT_ASC) String sortOrder,
                                                            @RequestParam(value = "search", defaultValue = "") String search) {

        return ResponseEntity
                .ok(approvalProductService.getPageableApprovalProduct(pageNo, pageSize, sortField, sortOrder, search));
    }

    @PostMapping("/approval-product")
    public ResponseEntity<?> createApprovalProduct(@RequestBody ApprovalProductDetailDTO dto) {
        System.out.println("TestController: RUN createApprovalProduct");

        return ResponseEntity.ok(approvalProductService.createApprovalProduct(dto));
    }

    @PutMapping("/approval-product")
    public ResponseEntity<?> updateApprovalProductById(@RequestParam("id") Integer id,
                                                       @RequestBody ApprovalProductDetailDTO approvalProductDetailDTO) throws Exception {

        return ResponseEntity.ok(approvalProductService.updateApprovalProduct(id, approvalProductDetailDTO));
    }

    @DeleteMapping("/approval-product")
    public void deleteApprovalProductById(@RequestParam("id") Integer id) throws Exception {

        approvalProductService.deleteApprovalProduct(id);

    }

    @GetMapping("/approval-product/{id}")
    public ResponseEntity<ApprovalProductDetailDTO> getApprovalProductById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(approvalProductService.getApprovalProductDetail(id));
    }
}

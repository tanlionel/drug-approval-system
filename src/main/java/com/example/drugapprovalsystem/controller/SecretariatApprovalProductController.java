package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.service.ServiceInterface.ApprovalProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secretariat")
@AllArgsConstructor
@CrossOrigin
public class SecretariatApprovalProductController {
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

    @GetMapping("/approval-products-FDA/")
    public ResponseEntity<?> getApprovalProductWithPageableByFDA(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                 @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                                                 @RequestParam(value = "sortOrder", defaultValue = Common.SORT_ASC) String sortOrder,
                                                                 @RequestParam(value = "search", defaultValue = "") String search) {

        return ResponseEntity
                .ok(approvalProductService.getPageableApprovalProductByFDA(pageNo, pageSize, sortField, sortOrder, search));
    }
    @GetMapping("/approval-products-ANSM/")
    public ResponseEntity<?> getApprovalProductWithPageableByANSM(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                  @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                                                  @RequestParam(value = "sortOrder", defaultValue = Common.SORT_ASC) String sortOrder,
                                                                  @RequestParam(value = "search", defaultValue = "") String search) {

        return ResponseEntity
                .ok(approvalProductService.getPageableApprovalProductByANSM(pageNo, pageSize, sortField, sortOrder, search));
    }

    @GetMapping("/approval-products-DAV/")
    public ResponseEntity<?> getApprovalProductWithPageableByDAV(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                 @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                                                 @RequestParam(value = "sortOrder", defaultValue = Common.SORT_ASC) String sortOrder,
                                                                 @RequestParam(value = "search", defaultValue = "") String search) {

        return ResponseEntity
                .ok(approvalProductService.getPageableApprovalProductByDAV(pageNo, pageSize, sortField, sortOrder, search));
    }

    @GetMapping("/approval-product-detail")
    public ResponseEntity<?> getApprovalProductById(@RequestParam Integer id) throws Exception {
        return ResponseEntity.ok(approvalProductService.getApprovalProductDetail(id));
    }
}
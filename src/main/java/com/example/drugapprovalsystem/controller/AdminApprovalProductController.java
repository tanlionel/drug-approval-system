package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ApprovalProductRequestDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.ApprovalProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@CrossOrigin
public class AdminApprovalProductController {
    @Autowired
    ApprovalProductService approvalProductService;

    // ============== Approval product =======================
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

    @GetMapping("/approval-products")
    public ResponseEntity<?> getApprovalProductWithPageable(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                                            @RequestParam(value = "sortOrder", defaultValue = Common.SORT_ASC) String sortOrder,
                                                            @RequestParam(value = "search", defaultValue = "") String search) {

        return ResponseEntity
                .ok(approvalProductService.getPageableApprovalProduct(pageNo, pageSize, sortField, sortOrder, search));
    }

    @PostMapping("/approval-products/create")
    public ResponseEntity<?> createApprovalProduct(@RequestBody ApprovalProductRequestDTO dto) throws Exception{
        return ResponseEntity
                .ok(approvalProductService.createApprovalProduct(dto));
    }

    @PutMapping("/approval-product/update")
    public ResponseEntity<?> updateApprovalProductById(@RequestParam("id") Integer id,
                                                       @RequestBody ApprovalProductRequestDTO approvalProductDetailDTO) throws Exception {
        return ResponseEntity.ok(approvalProductService.updateApprovalProduct(id, approvalProductDetailDTO));
    }

    @DeleteMapping("/approval-product/delete")
    public void deleteApprovalProductById(@RequestParam("id") Integer id) throws Exception {
        approvalProductService.deleteApprovalProduct(id);
    }

    @GetMapping("/approval-product-detail")
    public ResponseEntity<?> getApprovalProductById(@RequestParam Integer id) throws Exception {
        return ResponseEntity
                .ok(approvalProductService.getApprovalProductDetail(id));
    }
    // ============== Approval product =======================
}

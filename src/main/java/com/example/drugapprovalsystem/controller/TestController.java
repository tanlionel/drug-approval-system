package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.model.DTO.DrugRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ApprovalProductDetailDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.ApprovalProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.CategoryService;
import com.example.drugapprovalsystem.service.ServiceInterface.CountryService;
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
    @Autowired
    ApprovalProductService approvalProductService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CountryService countryService;
    @GetMapping("/test/drug")
    public ResponseEntity<?> getDrugPageable(@RequestParam(defaultValue = "0") int pageNo,
                                             @RequestParam(defaultValue = "10") int pageSize,
                                             @RequestParam(defaultValue = "id") String sortField,
                                             @RequestParam(defaultValue = Common.SORT_ASC) String sortOrder,
                                             @RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(drugService.getDrugPageable(pageNo,pageSize,sortField,sortOrder, search));
    }

    @PostMapping("/test/drug")
    public void createDrug(@RequestBody DrugRequestDTO drugRequestDTO){
        drugService.createDrug(drugRequestDTO);
    }

    @GetMapping("/test/admin/approval-product")
    public ResponseEntity<?> getApprovalProductWithPageable(@RequestParam(defaultValue = "0") int pageNo,
                                                            @RequestParam(defaultValue = "10") int pageSize,
                                                            @RequestParam(defaultValue = "id") String sortField,
                                                            @RequestParam(defaultValue = Common.SORT_ASC) String sortOrder,
                                                            @RequestParam(defaultValue = "") String search) {

        return ResponseEntity.ok(approvalProductService.getPageableApprovalProduct(pageNo, pageSize, sortField, sortOrder, search));
    }

    @PutMapping("/test/drug")
    public void updateByDrugId(@RequestParam("id") Integer id,
                               @RequestBody DrugRequestDTO dto) throws Exception{
        drugService.updateDrug(dto);
    }

    @GetMapping("/test/public/category")
    public ResponseEntity<?> getAllCategory(@RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(categoryService.getAllCategoryByTitle(search));
    }

    @GetMapping("/test/public/countries")
    public ResponseEntity<?> getAllCountry(@RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(countryService.getAllCountryByName(search));
    }

    @PostMapping("/test/admin/approval-product")
    public ResponseEntity<?> createApprovalProduct(@RequestBody ApprovalProductDetailDTO dto) throws Exception{
        System.out.println("TestController: RUN createApprovalProduct");

        return ResponseEntity.ok(approvalProductService.createApprovalProduct(dto));
    }

    @PutMapping("test/admin/approval-product")
    public ResponseEntity<?> updateApprovalProductById(@RequestParam("id") Integer id,
                                                              @RequestBody ApprovalProductDetailDTO approvalProductDetailDTO) throws Exception {

        return ResponseEntity.ok(approvalProductService.updateApprovalProduct(id, approvalProductDetailDTO));
    }

    @DeleteMapping("test/admin/approval-product")
    public void deleteApprovalProductById(@RequestParam("id") Integer id) throws Exception {

        approvalProductService.deleteApprovalProduct(id);

    }

    @GetMapping("/test/approval-product-detail")
    public ResponseEntity<?> getApprovalProductById(@RequestParam Integer id) throws Exception {
        return ResponseEntity
                .ok(approvalProductService.getApprovalProductDetail(id));
    }
}

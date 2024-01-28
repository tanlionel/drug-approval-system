package com.example.drugapprovalsystem.controller;

import com.example.drugapprovalsystem.model.DTO.DrugRequestDTO;
import com.example.drugapprovalsystem.service.ServiceInterface.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.entity.ApprovalProduct;
import com.example.drugapprovalsystem.entity.Ingredient;
import com.example.drugapprovalsystem.model.DTO.product_dto.ApprovalProductRequestDTO;
import com.example.drugapprovalsystem.repository.ApprovalProductRepository;
import com.example.drugapprovalsystem.repository.IngredientRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.ApprovalProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.CategoryService;
import com.example.drugapprovalsystem.service.ServiceInterface.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
@CrossOrigin
public class TestController {
    @Autowired
    ApprovalProductService approvalProductService;
    @Autowired
    ApprovalProductRepository approvalProductRepository;
    @Autowired
    IngredientRepository ingredientRepository;
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

    @Autowired
    DrugService drugService;
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

//    @PutMapping("test/admin/approval-product")
//    public void deleteIngredientByApprovalProductId(@RequestParam("id") Integer id,
//                                                    @RequestBody ApprovalProductRequestDTO dto) {
//
//        dto.setId(id);
//        approvalProductService.updateApprovalProduct(dto);
//    }
}

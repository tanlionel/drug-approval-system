package com.example.drugapprovalsystem.service.ServiceInterface;

import com.example.drugapprovalsystem.model.DTO.product_request_dto.ApprovalProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ProductDetailResponseDTO;

public interface ProductService {

    public ProductDetailResponseDTO createProduct(ProductRequestDTO productDetailDTO) throws Exception;
    public ProductDetailResponseDTO getProductDetail(Integer id) throws Exception;
    public ProductDetailResponseDTO updateProduct(Integer id, ProductRequestDTO productDetailDTO) throws Exception;
}

package com.example.drugapprovalsystem.service.ServiceInterface;

import com.example.drugapprovalsystem.model.DTO.product_request_dto.ProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ProductDetailResponseDTO;

public interface ProductService {

    public ProductDetailResponseDTO createProduct(ProductRequestDTO approvalProductDetailDTO) throws Exception;
    public ProductDetailResponseDTO getProductDetail(Integer id) throws Exception;
    public ProductDetailResponseDTO updateProduct(Integer id, ProductRequestDTO approvalProductDetailDTO) throws Exception;
}

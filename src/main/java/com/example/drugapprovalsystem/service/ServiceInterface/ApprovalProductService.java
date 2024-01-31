package com.example.drugapprovalsystem.service.ServiceInterface;

import com.example.drugapprovalsystem.model.DTO.product_request_dto.ApprovalProductDetailDTO;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductResponseDTO;
import org.springframework.data.domain.Page;

public interface ApprovalProductService {
    public Page<ApprovalProductResponseDTO> getPageableApprovalProduct(int pageNo, int pageSize,
                                                                       String sortField, String sortOrder,
                                                                       String search);
    public ApprovalProductDetailResponseDTO createApprovalProduct(ProductRequestDTO approvalProductDetailDTO) throws Exception;

    public ApprovalProductDetailResponseDTO updateApprovalProduct(Integer id, ProductRequestDTO approvalProductDetailDTO) throws Exception;
    public void deleteApprovalProduct(Integer id) throws Exception;
    public void activeApproveProduct(Integer id) throws Exception;
    public ApprovalProductDetailResponseDTO getApprovalProductDetail(Integer id) throws Exception;
}

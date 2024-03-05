package com.example.drugapprovalsystem.service.ServiceInterface;

import com.example.drugapprovalsystem.exception.ProductDoesNotExistException;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.ApprovalProductRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductDetailResponseDTO;
import com.example.drugapprovalsystem.model.DTO.product_response_dto.ApprovalProductResponseDTO;
import org.springframework.data.domain.Page;

public interface ApprovalProductService {
    public Page<ApprovalProductResponseDTO> getPageableApprovalProductByFDA(int pageNo, int pageSize,
                                                                       String sortField, String sortOrder,
                                                                       String search);

    public Page<ApprovalProductResponseDTO> getPageableApprovalProductByANSM(int pageNo, int pageSize,
                                                                            String sortField, String sortOrder,
                                                                            String search);

    public Page<ApprovalProductResponseDTO> getPageableApprovalProductByDAV(int pageNo, int pageSize,
                                                                            String sortField, String sortOrder,
                                                                            String search);
    public Page<ApprovalProductResponseDTO> getPageableApprovalProduct(int pageNo, int pageSize,
                                                                       String sortField, String sortOrder,
                                                                       String search);
    public ApprovalProductDetailResponseDTO createApprovalProduct(ApprovalProductRequestDTO approvalProductDetailDTO) throws Exception;
    public ApprovalProductDetailResponseDTO updateApprovalProduct(Integer id, ApprovalProductRequestDTO approvalProductDetailDTO) throws Exception;
    public void deleteApprovalProduct(Integer id) throws Exception;
    public void activeApproveProduct(Integer id) throws Exception;
    public ApprovalProductDetailResponseDTO getApprovalProductDetail(Integer id) throws Exception;
    ApprovalProductResponseDTO uploadImage(Integer approvalProductID, String imageLink) throws ProductDoesNotExistException;
}

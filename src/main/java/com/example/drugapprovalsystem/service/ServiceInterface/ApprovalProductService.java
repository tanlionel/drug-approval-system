package com.example.drugapprovalsystem.service.ServiceInterface;

import com.example.drugapprovalsystem.entity.ApprovalProduct;
import com.example.drugapprovalsystem.model.DTO.ApprovalProductResponseDTO;
import org.springframework.data.domain.Page;

public interface ApprovalProductService {
    public Page<ApprovalProductResponseDTO> getPageableApprovalProduct(int pageNo, int pageSize,
                                                                       String sortField, String sortOrder,
                                                                       String search);

}

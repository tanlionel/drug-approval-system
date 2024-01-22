package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.entity.ApprovalProduct;
import com.example.drugapprovalsystem.model.DTO.ApprovalProductResponseDTO;
import com.example.drugapprovalsystem.model.Mapper.ProductMapper;
import com.example.drugapprovalsystem.model.Mapper.UserMapper;
import com.example.drugapprovalsystem.repository.ApprovalProductRepository;
import com.example.drugapprovalsystem.service.ServiceInterface.ApprovalProductService;
import com.example.drugapprovalsystem.service.ServiceInterface.PageableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class ApprovalProductServiceImplement implements ApprovalProductService {
    private final String SORT_ASC = "ASC";
    @Autowired
    PageableService pageableService;
    @Autowired
    ApprovalProductRepository approvalProductRepository;
    @Override
    public Page<ApprovalProductResponseDTO> getPageableApprovalProduct(int pageNo, int pageSize,
                                                                       String sortField, String sortOrder,
                                                                       String search) {
        Pageable pageable = pageableService.getPageableWithSort(pageNo, pageSize, sortField, sortOrder);

        Page<ApprovalProduct> page = approvalProductRepository.findAllByNameContaining(pageable, search);

        return page.map(ProductMapper::mapToApprovalProductResponseDTO);
    }
}

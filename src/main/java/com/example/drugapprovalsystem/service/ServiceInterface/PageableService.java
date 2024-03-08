package com.example.drugapprovalsystem.service.ServiceInterface;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface PageableService {
    public Pageable getPageableWithSort(int pageNo, int pageSize, String sortField, String sortBy);

    public Pageable getPageable(int pageNo, int pageSize);
}

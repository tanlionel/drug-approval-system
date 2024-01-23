package com.example.drugapprovalsystem.service.ServiceImplement;

import com.example.drugapprovalsystem.common.Common;
import com.example.drugapprovalsystem.service.ServiceInterface.PageableService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PageableServiceImplement implements PageableService {
    @Override
    public Pageable getPageableWithSort(int pageNo, int pageSize,
                                               String sortField, String sortBy) {
        Pageable pageable = null;

        sortField = (sortField ==  null) ? "id" : sortField;

        if (sortBy != null && sortBy.equals(Common.SORT_ASC))
            pageable = PageRequest.of(pageNo, pageSize).withSort(Sort.by(sortField).ascending());
        else
            pageable = PageRequest.of(pageNo, pageSize).withSort(Sort.by(sortField).descending());

        return pageable;
    }

    @Override
    public Pageable getPageable(int pageNo, int pageSize) {
        Pageable pageable = null;

        pageable = PageRequest.of(pageNo, pageSize);

        return pageable;
    }

}

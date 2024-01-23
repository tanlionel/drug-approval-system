package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.entity.*;
import com.example.drugapprovalsystem.model.DTO.product_dto.AuthorityRequestDTO;
import com.example.drugapprovalsystem.model.DTO.product_dto.DrugIngredientsRequestDTO;

public class AuthorityMapper {

    public final static Authority mapToAuthority(AuthorityRequestDTO auth, Integer productId, boolean isApprovalProduct) {
        Authority authority = new Authority();

        authority.setCertificateName(auth.getCertificateName());
        authority.setCountry(new Country(auth.getCountryId()));

        if (isApprovalProduct)
            authority.setApprovalProduct(new ApprovalProduct(productId));
        else
            authority.setProduct(new Product(productId));

        return authority;
    }

}

package com.example.drugapprovalsystem.model.Mapper;

import com.example.drugapprovalsystem.entity.*;
import com.example.drugapprovalsystem.model.DTO.AuthorityDTO;
import com.example.drugapprovalsystem.model.DTO.product_request_dto.AuthorityRequestDTO;

public class AuthorityMapper {

    public static Authority mapToAuthority(AuthorityRequestDTO auth, Integer productId, boolean isApprovalProduct) {
        Authority authority = new Authority();

        authority.setCertificateName(auth.getCertificateName());
        authority.setCountry(new Country(auth.getCountryId()));

        if (isApprovalProduct)
            authority.setApprovalProduct(new ApprovalProduct(productId));
        else
            authority.setProduct(new Product(productId));

        return authority;
    }

    public static AuthorityDTO mapToAuthorityDTO(Authority auth) {
        return AuthorityDTO.builder()
                .certificateName(auth.getCertificateName())
                .countryId(auth.getCountry() == null ? null : auth.getCountry().getId())
                .countryName(auth.getCountry() == null ? null : auth.getCountry().getName())
                .build();
    }

}
